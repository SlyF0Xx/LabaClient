package Laba2;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

/**
 * Created by SlyFox on 02.05.2017.
 */
public class HostCommands {
    private static InetAddress Addr;
    private static DatagramSocket serverSocket;

    public static void SetInetAddress(InetAddress inetAddress)
    {
        Addr = inetAddress;
        try {
            serverSocket = new DatagramSocket(2222, Addr);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void SetInetAddress()
    {
        try {
            Addr = InetAddress.getByName("localhost");
            serverSocket = new DatagramSocket(2222, Addr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ReceiveCommand()
    {
        byte[] receiveData = new byte[1];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            serverSocket.receive(receivePacket);
            //ByteBuffer.allocate(4).put(receivePacket.getData()).getInt()
            System.out.println(receivePacket.getData()[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer ReceiveInteger()
    {
        byte[] receiveData = new byte[4];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            serverSocket.receive(receivePacket);

            return ByteBuffer.allocate(4).put(receivePacket.getData()).getInt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object RecieveObject()
    {
        byte[] receiveData = new byte[ReceiveInteger()];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            serverSocket.receive(receivePacket);

            ByteArrayInputStream t = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(t);

            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    public static void SendObject(Object object)
    {
        try {
            if(object instanceof String)
            {
                clientSocket.send(new DatagramPacket(String.valueOf(((String) object).getBytes().length).getBytes(), 4));
                clientSocket.send(new DatagramPacket(((String) object).getBytes(), ((String) object).getBytes().length));
            }
            else if(object instanceof Integer)
            {
                clientSocket.send(new DatagramPacket(object.toString().getBytes(), 4));
            }
            else
            {
                ByteArrayOutputStream t = new ByteArrayOutputStream();

                ObjectOutputStream oos = new ObjectOutputStream(t);

                oos.writeObject(object);

                clientSocket.send(new DatagramPacket(String.valueOf(t.toByteArray().length).getBytes(), 4));
                clientSocket.send(new DatagramPacket(t.toByteArray(), t.toByteArray().length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object ReciveObject()
    {
        int lenght;
        byte[] lengh = new byte[4];
        byte[] data;
        try {
            clientSocket.receive(new DatagramPacket(lengh, 4));
            lenght = Integer.valueOf(new String(lengh));
            data = new byte[lenght];
            clientSocket.receive(new DatagramPacket(data, lenght));

            ByteArrayInputStream t = new ByteArrayInputStream(data);

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
        byte[] data = new byte[4];
        try {
            clientSocket.receive(new DatagramPacket(data, 4));
            return Integer.valueOf(new String(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void SendCommand(String name)
    {
        try {
            clientSocket.send(new DatagramPacket(new byte[]{RequestsResponcesTable.getRequestByName(name)}, 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
