/*
 * Developed by - mGunawardhana
 * Contact email - mrgunawardhana27368@gmail.com
 * what's app - 071 - 9043372
 */

package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public TextField userNameTxt;
    public PasswordField passwordField;
    public AnchorPane LoginAnchorPane;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        /* client one form loading on action */

        /* checking requirements for client one -------------------------------------------------------- */
        if (userNameTxt.getText().trim().equals("maneesha")) {
            if (passwordField.getText().trim().equals("12345")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ui/ClientForm.fxml"));
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                ClientFormController clientFormController = fxmlLoader.getController();
                clientFormController.setController(LoginAnchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                userNameTxt.clear();
                passwordField.clear();
            }

            /* client two form loading option */

            /* checking requirements for client two -------------------------------------------------------- */
        } else if (userNameTxt.getText().trim().equals("bashi")) {
            if (passwordField.getText().trim().equals("12345")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ui/ClientForm2.fxml"));
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                ClientFormController2 clientFormController2 = fxmlLoader.getController();
                clientFormController2.setController2(LoginAnchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                userNameTxt.clear();
                passwordField.clear();
            }

            /* client three form loading option */

            /* checking requirements for client three -------------------------------------------------------- */
        } else if (userNameTxt.getText().trim().equals("dinusha")) {
            if (passwordField.getText().trim().equals("12345")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ui/ClientForm3.fxml"));
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                ClientFormController3 clientFormController3 = fxmlLoader.getController();
                clientFormController3.setController3(LoginAnchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                userNameTxt.clear();
                passwordField.clear();
            }
        }
    }
}