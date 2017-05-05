package Visual;

import Laba2.People;
import Laba2.Person;
import Laba2.RequestsResponcesTable;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Map;

/**
 * Created by SlyFox on 26.04.2017.
 */
public class Updater{
    private static InetSocketAddress  Addr;
    private static  DatagramChannel clientChannel;


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
            clientChannel = DatagramChannel.open();

            Addr = new InetSocketAddress("localhost", 2222);

            clientChannel.connect(Addr);
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
            return buffer.getInt(0);
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
            return (Person) ReciveObject();
        }
        else return null;
        //return People.GetByName(name);
    }

    static Map<String, Person> GetAll()
    {
        if(Addr!=null)
        {
            SendCommand("GetPersons");
            return (Map<String, Person>) ReciveObject();
        }
        else return null;
        //return People.GetPersons();
    }

    static void EditPerson(String name, Person newValue)
    {
        //People.GetPersons().
    }

    static void EditPersons(Map<String, Person> persons)
    {
        People.GetPersons().clear();
        People.GetPersons().putAll(persons);
    }

    static void AddPerson(Person Value)
    {
        if(Addr!=null)
        {
            SendCommand("AddPerson");
            SendObject(Value);
        }

        //People.AddPerson(Value);
    }

    static void AddPersons(Map<String, Person> persons)
    {
        if(Addr!=null)
        {
            SendCommand("AddPersons");
            SendObject(persons.size());
            persons.forEach((i,j)->SendObject(j));


            //SendObject(Value);
        }

        //People.AddPerson(Value);
    }

    static void DeletePerson(String name)
    {
        if(Addr!=null)
        {
            SendCommand("DeletePerson");
            SendObject(name);
        }
        //People.GetPersons().remove(name);
    }
}
