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

public class ClientFormController2 {

    /* emoji unicode holders */
    static String emo1 = "",emo2 = "",emo3 = "";

    /* client one port */
    final int PORT = 8000;

    /* emoji tab */
    public AnchorPane emojiPane;
    public AnchorPane clientForm3;

    /* initializing socket */
    Socket socket2;

    /* initializing dataInputStream and dataOutputStream */
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;

    /* initializing String var for holding InputStream and OutputStream value */
    String message2 = "";

    @FXML
    public TextArea textArea2;

    @FXML
    public TextField textMessage2;

    public void initialize() {
        emo1 = "";
        emo2 = "";
        emo3 = "";

        emojiPane.setVisible(false);
        new Thread(() -> {

            try {

                socket2 = new Socket("localhost", PORT);

                dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
                dataInputStream2 = new DataInputStream(socket2.getInputStream());

                /* getting / reading messages from server side */
                while (!message2.equals("exit")) {
                    message2 = dataInputStream2.readUTF();
                    textArea2.appendText(message2 + "\n");

                }

                socket2.close();

                /* closing input and output streams */
                dataOutputStream2.close();
                dataInputStream2.close();

            } catch (IOException ignored) {
            }

        }).start();
    }

    /* text message sender - on action */
    public void sendOnAction2(ActionEvent actionEvent) throws IOException {
        dataOutputStream2.writeUTF(textMessage2.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream2.flush();
    }

    /* on action for opening emoji window */
    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    /* setting up file chooser for client two */
    public void imageSendOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    /* emoji pane disabling on-action */
    public void textMessage(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
    }

    /* emoji unicode assigning section -------------- */
    public void l1emoOnAction(MouseEvent mouseEvent) {
        textMessage2.setText(textMessage2.getText()+ "\uD83D\uDE42");
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {
        textMessage2.setText(textMessage2.getText()+"\uD83D\uDE0D");
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
        textMessage2.setText(textMessage2.getText()+"\uD83E\uDD2A");
    }

    public void setController2(AnchorPane anchorPane){
        this.clientForm3=anchorPane;
    }
}
