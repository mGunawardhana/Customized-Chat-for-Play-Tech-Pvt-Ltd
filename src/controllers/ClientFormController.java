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

public class ClientFormController {

    static String emo1 = "";
    static String emo2 = "";
    static String emo3 = "";
    final int PORT = 5000;
    public AnchorPane emojiPane;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    @FXML
    private TextArea textArea;
    @FXML
    private TextField textMessage;

    public void initialize() {
        emo1 = "";
        emo2 = "";
        emo3 = "";

        emojiPane.setVisible(false);
        new Thread(() -> {
            try {

                accept = new Socket("localhost", PORT);

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")) {

                    message = dataInputStream.readUTF();
                    textArea.appendText(message+"\n");
                }

                dataOutputStream.writeUTF(message.trim() + emo1 + emo2 + emo3);
                dataOutputStream.flush();

                accept.close();
                dataOutputStream.close();
                dataInputStream.close();

            } catch (IOException ignored) {
            }

        }).start();

    }

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(
                textMessage.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream.flush();

    }

    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    public void imageSendOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    public void textMessage(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
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
}
