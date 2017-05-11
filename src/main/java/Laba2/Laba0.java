package Laba2;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Visual.Controller;
import Visual.Updater;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;


public class Laba0
    extends Application
{
    public boolean equals(Object obj)
    {
         return true;
    }

    public String toString()
    {
        return "main  class";
    }

    public int hashCode()
    {
        return 1;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(Laba0.class.getResource("/sample.fxml"));
            loader.setLocation(Laba0.class.getResource("../sample.fxml"));

            Parent root = loader.load();
            primaryStage.setTitle("Laba");
            primaryStage.setFullScreen(false);
            Scene scene = new Scene(root, 300, 275);
            primaryStage.setScene(scene);


            Controller controller = loader.getController();
            controller.setMain(this, primaryStage);

            primaryStage.heightProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    controller.Zoom();
                }
            });

            scene.getAccelerators().put(
                    new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN),
                    new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            controller.GenerateRandom();
                            // just do action
                        }
                    }
            );

            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RequestsResponcesTable();

        launch(args);

        Updater.Disconect();
    }
}
