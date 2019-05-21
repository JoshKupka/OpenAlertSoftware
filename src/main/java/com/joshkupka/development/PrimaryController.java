package com.joshkupka.development;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private ImageView profileImageView;
    @FXML
    private Label streamerTypeLabel;
    @FXML
    private Label usernameLabel;

    private Database database = new Database();

    @FXML
    public void initialize() {
        try {
            Path path = Paths.get("UserData.json");
            if (Files.exists(path) && !(database.containsKey("First-Run"))) {
                System.out.println("YES");
                displayData();
            } else {
                System.out.println("NO");
                database.databaseInit();
                database.putData("First-Run", true);
                Stage popup = new Stage();
                popup.initOwner(popup.getOwner());
                popup.setResizable(false);
                popup.setTitle("Sign In");
                popup.setScene(new Scene(FXMLLoader.load(getClass().getResource("signin.fxml"))));
                popup.showAndWait();
                displayData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayData() {
        Platform.runLater(() -> streamerTypeLabel.setText(database.getData("Broadcaster-Type").toString()));
        Platform.runLater(() -> usernameLabel.setText(database.getData("Display-Name").toString()));
        try {
            FileInputStream resource = new FileInputStream(new File("profileImage.png"));
            Platform.runLater(() -> profileImageView.setImage(new Image(resource)));
            Platform.runLater(() -> profileImageView.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
