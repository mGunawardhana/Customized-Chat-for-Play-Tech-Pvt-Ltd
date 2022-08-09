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

public class ClientFormController2 {
    public TextArea textArea2;
    public TextField textMessage2;

    final int PORT = 8000;
    Socket socket2;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;
    String message2 = "";

    public void initialize() {
        new Thread(() -> {

            try {
                socket2 = new Socket("localhost", PORT);


                dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
                dataInputStream2 = new DataInputStream(socket2.getInputStream());

                while (!message2.equals("exit")) {
                    message2 = dataInputStream2.readUTF();
                    textArea2.appendText(message2 + "\n");

                }

            } catch (IOException ignored) {
            }

        }).start();
    }
    public void sendOnAction2(ActionEvent actionEvent) throws IOException {
        dataOutputStream2.writeUTF(textMessage2.getText().trim());
        dataOutputStream2.flush();
    }

    public void imoSendOnAction(MouseEvent mouseEvent) {
    }

    public void imageSendOnAction(MouseEvent mouseEvent) {
    }
}
