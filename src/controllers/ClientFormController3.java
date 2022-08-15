/*
 * Developed by - mGunawardhana
 * Contact email - mrgunawardhana27368@gmail.com
 * what's app - 071 - 9043372
 */

package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController3 {

    /* emoji unicode holders */
    static String emo1 = "";
    static String emo2 = "";
    static String emo3 = "";

    /* client three port */
    final int port3 = 1234;

    /* emoji tab */
    public AnchorPane emojiPane;
    public AnchorPane clientAnchorPane3;

    /* initializing socket */
    Socket socket3;

    /* initializing dataInputStream and dataOutputStream */
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;

    /* initializing String var for holding InputStream and OutputStream value */
    String message3 = "";

    @FXML
    public TextArea textArea3;

    @FXML
    public TextField textMessage3;


    public void initialize() {
        emo1 = "";
        emo2 = "";
        emo3 = "";

        emojiPane.setVisible(false);
        new Thread(() -> {

            try {
                socket3 = new Socket("localhost", port3);

                dataInputStream3 = new DataInputStream(socket3.getInputStream());
                dataOutputStream3 = new DataOutputStream(socket3.getOutputStream());

                /* getting / reading messages from server side */
                while (!message3.equals("exit")) {
                    message3 = dataInputStream3.readUTF();
                    textArea3.appendText(message3 + "\n");
                }

                socket3.close();
                dataOutputStream3.close();
                dataInputStream3.close();

            } catch (IOException ignored) {
            }
        }).start();
    }

    /* text message sender - on action */
    public void sendOnAction3(ActionEvent actionEvent) throws IOException {
        dataOutputStream3.writeUTF(textMessage3.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream3.flush();
        emojiPane.setVisible(false);
    }

    /* on action for opening emoji window */
    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    /* setting up file chooser for client three */
    public void imageSendOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    /* emoji pane disabling on-action */
    public void textMessage3(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
    }

    /* emoji unicode assigning section */
    public void l1emoOnAction(MouseEvent mouseEvent) {
        textMessage3.setText(textMessage3.getText()+"\uD83D\uDE42");
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {
        textMessage3.setText(textMessage3.getText()+"\uD83D\uDE0D");
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
        textMessage3.setText(textMessage3.getText()+"\uD83E\uDD2A");
    }

    public void setController3(AnchorPane anchorPane){
        this.clientAnchorPane3=anchorPane;
    }
}
