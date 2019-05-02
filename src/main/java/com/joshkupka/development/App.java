package com.joshkupka.development;

import javafx.application.Application;
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

        stage.setTitle("JK Streamer Suite");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Path path = Paths.get("UserData.json");
        if(Files.exists(path)){
            System.out.println("YES");
        } else {
            System.out.println("NO");

            database.databaseInit();
            database.putData("First-Run", true);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}