package Laba2;

import Visual.Controller;
import Visual.Model;
import javafx.application.Platform;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by SlyFox on 17.05.2017.
 */
public class Reciver extends Thread {
    private static volatile  boolean PrepareForTrnsmiting;
    private static DatagramChannel clientChannel;
    private static Model model;

    public static synchronized boolean IsPrepareForTrnsmiting() {
        return PrepareForTrnsmiting;
    }

    public Reciver(DatagramChannel clientChannel) {
        this.clientChannel = clientChannel;
        PrepareForTrnsmiting = false;
    }

    public static void setModel(Model arg) {
        model = arg;
    }

    public static synchronized void endTransmiting() {
        PrepareForTrnsmiting = false;
    }

    @Override
    public void run() {
        while (true) {
            if (!PrepareForTrnsmiting) {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1);
                    clientChannel.receive(buffer);
                    switch (RequestsResponcesTable.getResponceByValue(buffer.get(0))) {
                        case "Value": {
                            PrepareForTrnsmiting = true;
                            break;
                        }
                        case "Announcement": {
                            Platform.runLater(() -> Controller.InitAlert("Anons"));
                            if (model != null) {
                                Thread temp = new Thread()
                                {
                                    @Override
                                    public void run()
                                    {
                                        model.Update();
                                    }
                                };
                                temp.start();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}