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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public TextArea textArea;
    public TextField textMessage;

    final int PORT = 5000;
    final int port = 8000;
    final int port3 = 1234;

    Socket accept;
    ServerSocket serverSocket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    public void initialize(){

//        client one thread
        new Thread(() -> {
            try{
                serverSocket = new ServerSocket(PORT);
                textArea.appendText("Server Started..\n");
                accept = serverSocket.accept();
                textArea.appendText("\nClient 1 Connected..");

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    textArea.appendText("\nClient 1 : " + message);

                    dataOutputStream.writeUTF("Client 1 : " + message.trim());
                    dataOutputStream.flush();
                }
            }catch (IOException ignored){}
        }).start();
    }


    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF("Server : " + textMessage.getText().trim());
        dataOutputStream.flush();
    }
}
