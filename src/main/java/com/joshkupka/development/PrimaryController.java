package com.joshkupka.development;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Executable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.CredentialManagerBuilder;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

public class PrimaryController {

    @FXML
    private ImageView profileImageView;
    @FXML
    private Label streamerTypeLabel;
    @FXML
    private Label usernameLabel;

    private Database database = new Database();

    private ExecutorService service = Executors.newSingleThreadExecutor();
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

/*
    @FXML
    private void signInProcess() {
        if (database.containsKey("First-Run") && !(database.containsKey("User-Data"))) {
            try {
                String key = authenticate();
                if (!(database.containsKey("apiKey"))) {
                    database.putData("apiKey", key);
                }
                String userData = getUserData();
                if (!(database.containsKey("User-Data"))) {
                    database.putData("User-Data", userData);
                }
                String userID = database.profileID();
                if (!(database.containsKey("UUID"))) {
                    database.putData("UUID", userID);
                }
                String login = database.login();
                if (!(database.containsKey("login"))) {
                    database.putData("login", login);
                }
                String displayName = database.displayName();
                if (!(database.containsKey("Display-Name"))) {
                    database.putData("Display-Name", displayName);
                }
                String broadcasterType = database.broadcasterType();
                if (!(database.containsKey("Broadcaster-Type"))) {
                    database.putData("Broadcaster-Type", broadcasterType);
                }
                String profileImageURL = database.profileImageURL();
                if (!(database.containsKey("profileImageURL"))) {
                    database.putData("profileImageURL", profileImageURL);
                }
                try (InputStream in = new URL(profileImageURL).openStream()) {
                    Files.copy(in, Paths.get("profileImage.png"));
                }
                displayData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            displayData();
        }
    }



    public void displayData() {
        streamerTypeLabel.setText(database.getData("Broadcaster-Type").toString());
        usernameLabel.setText(database.getData("Display-Name").toString());
        try {
            FileInputStream resource = new FileInputStream(new File("profileImage.png"));
            profileImageView.setImage(new Image(resource));
            profileImageView.setVisible(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
*/

}
