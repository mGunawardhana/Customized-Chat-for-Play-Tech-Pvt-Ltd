/*
 * Developed by - mGunawardhana
 * Contact email - mrgunawardhana27368@gmail.com
 * what's app - 071 - 9043372
 */

package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFormController {

    public TextField userNameTxt;
    public PasswordField passwordField;

    public void loginOnAction(ActionEvent actionEvent) {

        if (userNameTxt.getText().trim().equals("maneesha")) {
            if (passwordField.getText().trim().equals("12345")) {
                //TODO : load here in client one
            } else {
                System.out.println("Incorrect try!");
            }
        } else if (userNameTxt.getText().trim().equals("sasmitha")) {
            if (passwordField.getText().trim().equals("12345")) {
                //TODO : load here in client two
            } else {
                System.out.println("Incorrect try!");
            }
        } else if (userNameTxt.getText().trim().equals("null")) {
            if (passwordField.getText().trim().equals("12345")) {
                //TODO : load here in client three
            }
        } else {
            System.exit(0);
        }

    }
}

