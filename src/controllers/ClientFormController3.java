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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController3 {

    public TextArea textArea3;
    public TextField textMessage3;
    final int port3 = 1234;
    Socket socket3;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;
    String message3 = "";

    public void initialize() {
        new Thread(() -> {

            try {
                socket3 = new Socket("localhost", port3);

                dataInputStream3 = new DataInputStream(socket3.getInputStream());
                dataOutputStream3 = new DataOutputStream(socket3.getOutputStream());

                while (!message3.equals("exit")) {
                    message3 = dataInputStream3.readUTF();
                    textArea3.appendText(message3+"\n");
                }

            } catch (IOException ignored) {
            }
        }).start();
    }


    public void sendOnAction3(ActionEvent actionEvent) throws IOException {
        dataOutputStream3.writeUTF(textMessage3.getText().trim());
        dataOutputStream3.flush();
    }

    public void emoSendOnAction(MouseEvent mouseEvent) {
    }

    public void imageSendOnAction(MouseEvent mouseEvent) {
    }
}
