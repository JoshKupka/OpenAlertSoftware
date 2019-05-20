package com.joshkupka.development;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SignInMenuController {

    @FXML
    ImageView logoImageView;

    @FXML
    ImageView loginButtonImageView;


    public void initialize(){
        /*try {
            FileInputStream logo = new FileInputStream(new File("@com/joshkupka/development/logo.png"));
            logoImageView.setImage(new Image(logo));
            logo.close();
            FileInputStream login = new FileInputStream(new File("@com/joshkupka/development/loginButton.png"));
            loginButtonImageView.setImage(new Image(login));
            login.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


}
