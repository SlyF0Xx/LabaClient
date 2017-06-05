package Visual;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import Laba2.Person;
import Laba2.Reciver;
import Laba2.RequestsResponcesTable;

/**
 * Created by SlyFox on 26.04.2017.
 */
public class Updater{
    private static InetSocketAddress  Addr;
    private static  DatagramChannel clientChannel;
    private static Reciver  reciver;

    public static void Disconect()
    {
        try {
            //TODO Добавить команду отключение от сервера
            clientChannel.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SetInetAddress(InetSocketAddress inetAddress)
    {
        try {
            clientChannel = DatagramChannel.open();

            Addr = inetAddress;

            clientChannel.connect(Addr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SetInetAddress()
    {
        try {
            if(Addr == null)
            {
                //Addr = new InetSocketAddress("192.168.43.22", 2222);
                Addr = new InetSocketAddress("localhost", 2222);
            }
            if(clientChannel == null)
            {
                clientChannel = DatagramChannel.open();
                clientChannel.connect(Addr);
            }

            if(reciver==null)
            {
                reciver = new Reciver(clientChannel);
                reciver.start();
            }
            //clientChannel.bind(Addr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SendObject(Object object)
    {
        ByteBuffer buffer;

        try {
            if(object instanceof String)
            {
                ByteBuffer tmp= ByteBuffer.allocate(4).putInt(((String) object).getBytes().length);
                buffer = ByteBuffer.wrap(tmp.array());
                clientChannel.write(buffer);

                buffer = ByteBuffer.wrap(((String) object).getBytes());
                clientChannel.write(buffer);
            }
            else if(object instanceof Integer)
            {
                ByteBuffer tmp = ByteBuffer.allocate(4).putInt((Integer) object);
                buffer =  ByteBuffer.wrap(tmp.array());

                clientChannel.write(buffer);
            }
            else
            {
                ByteArrayOutputStream t = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(t);
                oos.writeObject(object);

                ByteBuffer tmp = ByteBuffer.allocate(4).putInt(t.toByteArray().length);
                buffer =  ByteBuffer.wrap(tmp.array());
                clientChannel.write(buffer);

                buffer = ByteBuffer.wrap(t.toByteArray());
                clientChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object ReciveObject()
    {
        ByteBuffer buffer;

        try {
            buffer = ByteBuffer.allocate(ReciveInteger());
            clientChannel.receive(buffer);

            ByteArrayInputStream t = new ByteArrayInputStream(buffer.array());

            ObjectInputStream oin = new ObjectInputStream(t);
            return oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer ReciveInteger() {
        ByteBuffer buffer = ByteBuffer.allocate(4);

        try {
            clientChannel.receive(buffer);

            int temp = buffer.getInt(0);
            if (temp<0) throw new RuntimeException("WTF"+temp);
            return temp;
            //return buffer.getInt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean ReciveBoolean()
    {
        ByteBuffer buffer = ByteBuffer.allocate(1);

        try {
            clientChannel.receive(buffer);
            return buffer.get(0)==1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ReciveResponce()
    {
        ByteBuffer buffer = ByteBuffer.allocate(1);

        try {
            clientChannel.receive(buffer);
            return RequestsResponcesTable.getResponceByValue(buffer.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void SendCommand(String name)
    {
        ByteBuffer buffer;

        try {
            //ByteBuffer.allocate(4).putInt((Integer) object);
            buffer = ByteBuffer.wrap(new byte[]{RequestsResponcesTable.getRequestByName(name)});

            clientChannel.write(buffer);

            //clientSocket.send(new DatagramPacket(new byte[]{RequestsResponcesTable.getRequestByName(name)}, 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Person GetPerson(String name)
    {
        if(Addr!=null)
        {
            SendCommand("GetPerson");
            SendObject(name);

            while(true)
            {
                if(Reciver.IsPrepareForTrnsmiting())
                {
                    Person temp = (Person) ReciveObject();
                    Reciver.endTransmiting();
                    return temp;
                }
            }

        }
        else return null;
    }

    static Map<String, Person> GetAll()
    {
        if(Addr!=null)
        {
            SendCommand("GetPersons");
            while(true)
            {
                if(Reciver.IsPrepareForTrnsmiting())
                {
                    Map<String, Person> temp =  (Map<String, Person>) ReciveObject();
                    Reciver.endTransmiting();
                    return temp;
                }
            }
        }
        else return null;
    }

    static void EditPerson(String name, Person newValue)
    {
        SendCommand("EditPerson");
        SendObject(name);
        SendObject(newValue);
    }

    public static void AddPerson(Person Value)
    {
        if(Addr!=null)
        {
            SendCommand("AddPerson");
            SendObject(Value);
        }
    }

    static void AddPersons(Map<String, Person> persons)
    {
        if(Addr!=null)
        {
            SendCommand("AddPersons");
            SendObject(persons.size());
            persons.forEach((i,j)->SendObject(j));
        }
    }

    public static void DeletePerson(String name)
    {
        if(Addr!=null)
        {
            SendCommand("DeletePerson");
            SendObject(name);
        }
    }

    static Set<String> GetCommandNames()
    {
        SendCommand("GetCommandNames");
        Object[] target;
        while(true)
        {
            if(Reciver.IsPrepareForTrnsmiting())
            {
                target = (Object[]) ReciveObject();
                Reciver.endTransmiting();
                break;
            }
        }
        Set<String> names = new LinkedHashSet<String>();

        for(Object i : target)
        {
            names.add((String)i);
        }
        return names;
    }

    static boolean ExecuteCommand(String name, String params)
    {
        SendCommand("ExecuteCommand");
        SendObject(name);
        SendObject(params);
        while(true)
        {
            if(Reciver.IsPrepareForTrnsmiting())
            {
                Boolean temp =  ReciveBoolean();
                Reciver.endTransmiting();
                return temp;
            }
        }
    }
}
