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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


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
    private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        try
        {
            this.primaryStage = primaryStage;

            FXMLLoader loader = new FXMLLoader();

            loader.setResources(ResourceBundle.getBundle("I18n.StatsBundle", new Locale("ru", "RU")));
            loader.setLocation(Laba0.class.getResource("/sample.fxml"));
            //loader.setLocation(Laba0.class.getResource("../sample.fxml"));

            Parent root = loader.load();
            primaryStage.setTitle("Laba");
            primaryStage.setFullScreen(false);
            Scene scene = new Scene(root, 300, 275);
            primaryStage.setScene(scene);

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

    public static void ChangeLocation(Locale locale)
    {
        try {
            FXMLLoader loader = new FXMLLoader();

            double width = primaryStage.getScene().getWidth();
            double height = primaryStage.getScene().getHeight();

            ((AnchorPane)(primaryStage.getScene().getRoot())).getChildren().clear();
            loader.setResources(ResourceBundle.getBundle("I18n.StatsBundle", locale));
            loader.setLocation(Laba0.class.getResource("/sample.fxml"));

            Parent root = loader.load();
            Scene rescene = new Scene(root, width , height);
            primaryStage.setScene(rescene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RequestsResponcesTable();

        launch(args);

        Updater.Disconect();
    }
}
