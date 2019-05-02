package com.joshkupka.development;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.CredentialManagerBuilder;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

public class PrimaryController {

    private Stage primaryStage;
    private Database database = new Database();
    private CredentialManager credentialManager = CredentialManagerBuilder.builder().build();
    private String url = "https://id.twitch.tv/oauth2/authorize?client_id=v2g4ds9qldblz50i4qic8zzxfsja1n&redirect_uri=http://localhost&response_type=token&scope=user:edit+bits:read+channel_check_subscription+channel_editor+channel_read+channel_subscriptions+chat:edit+chat:read";

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

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    private String authenticate() {
        WebView webView = new WebView();
        webView.getEngine().load(url);

        Stage popup = new Stage();
        popup.setResizable(false);
        popup.setTitle("Sign-In");
        popup.setScene(new Scene((webView)));
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(primaryStage);

        StringBuilder result = new StringBuilder();
        webView.getEngine().locationProperty().addListener((observableValue, ov, url) -> {
            if (url.startsWith("http://localhost")) {
                String token = StringUtils.substringBetween(url, "=", "&");
                result.append(token);
                popup.close();
            }
        });
        popup.showAndWait();
        return result.toString();
    }

    private void displayData() {
    }

    private String getUserData() {
        Twitch twitch = new Twitch();
        String apikey = database.getData("apiKey").toString();
        String userData = twitch.getUser(apikey).toString();
        return userData;
    }
}
