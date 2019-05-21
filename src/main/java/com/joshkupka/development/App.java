package com.joshkupka.development;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Database database = new Database();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("OAS");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(915);
        stage.setMinHeight(543);
        stage.show();

        /*
        Path path = Paths.get("UserData.json");
        if(Files.exists(path) && !(database.containsKey("First-Run"))){
            System.out.println("YES");
            d.DisplayData();
        } else {
            System.out.println("NO");
            database.databaseInit();
            database.putData("First-Run", true);
            Stage popup = new Stage();
            popup.setResizable(false);
            popup.setTitle("Sign In");
            popup.setScene(new Scene(FXMLLoader.load(getClass().getResource("signin.fxml"))));
            popup.showAndWait();
        }
        */
    }
    public static void main(String[] args) {
        launch(args);
    }
}