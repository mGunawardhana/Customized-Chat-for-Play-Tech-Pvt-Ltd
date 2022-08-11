/*
 * Developed by - mGunawardhana
 * Contact email - mrgunawardhana27368@gmail.com
 * what's app - 071 - 9043372
 */

package controllers;

import javafx.event.ActionEvent;
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
    static String emo1 = "";
    static String emo2 = "";
    static String emo3 = "";
    final int PORT = 8000;
    public TextArea textArea2;
    public TextField textMessage2;
    public AnchorPane emojiPane;
    Socket socket2;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;
    String message2 = "";

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

                while (!message2.equals("exit")) {

                    message2 = dataInputStream2.readUTF();
                    textArea2.appendText(message2 + "\n");

                }

                socket2.close();
                dataOutputStream2.close();
                dataInputStream2.close();

            } catch (IOException ignored) {
            }

        }).start();
    }

    public void sendOnAction2(ActionEvent actionEvent) throws IOException {
        dataOutputStream2.writeUTF(textMessage2.getText().trim()+ emo1 + emo2 + emo3);
        dataOutputStream2.flush();
    }

    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    public void imageSendOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    public void l1emoOnAction(MouseEvent mouseEvent) {
        emo1 = "\uD83D\uDE42";
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {
        emo2 = "\uD83D\uDE0D";
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
        emo3 = "\uD83E\uDD2A";
    }

    public void textMessage(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
    }
}
