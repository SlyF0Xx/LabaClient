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
    private static Locale currentLocale;

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try
        {
            this.primaryStage = primaryStage;

            CreateWindow(new Locale("ru", "RU"), 600, 500);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void CreateWindow(Locale locale, double width, double height)
    {
        try {
            currentLocale = locale;
            FXMLLoader loader = new FXMLLoader();

            if(primaryStage.getScene() != null)
            {
                ((AnchorPane)(primaryStage.getScene().getRoot())).getChildren().clear();
            }

            loader.setResources(ResourceBundle.getBundle("I18n.StatsBundle", locale));
            loader.setLocation(Laba0.class.getResource("/sample.fxml"));

            Parent root = loader.load();
            primaryStage.setTitle("Laba");
            primaryStage.setFullScreen(false);
            Scene scene = new Scene(root, width , height);

            Controller controller = loader.getController();
            controller.setMain(this, primaryStage);

            primaryStage.setScene(scene);

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
