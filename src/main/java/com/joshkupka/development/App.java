package com.joshkupka.development;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

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
    }
    public static void main(String[] args) {
        launch(args);
    }
}