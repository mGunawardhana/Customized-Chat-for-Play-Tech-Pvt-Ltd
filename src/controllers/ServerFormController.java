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

    static String message3 = "";

    /* initializing static string holders for catching messages */
    private static String text_chat_one = "";
    private static String text_chat_two = "";
    private static String text_chat_three = "";

    /* initializing port numbers to interact with clients */
    final int PORT = 5000;
    final int port = 8000;
    final int port3 = 1234;
    public TextArea textArea;
    public TextField textMessage;

    /* initializing String var for holding InputStream and OutputStream value */
    String message = "";
    String message2 = "";

    /* initializing sockets to connect with Clients */
    Socket accept;
    Socket accept2;
    Socket accept3;

    /* initializing pack of dataInputStream and dataOutputStream */
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;

    /* initializing server sockets */
    ServerSocket serverSocket;
    ServerSocket serverSocket_client2;
    ServerSocket serverSocket_client3;

    public void initialize() {

        /* client one thread */
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                textArea.appendText("Server Started..\n");
                accept = serverSocket.accept();
                textArea.appendText("\nClient 1 Connected..");

                /* accepting input and output streams */
                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    textArea.appendText("\nClient 1 : " + message);

                    dataOutputStream.writeUTF("Client 1 : " + message.trim());
                    dataOutputStream.flush();

                    text_chat_one = message;

                    /* sending client one message to client two */
                    dataOutputStream2.writeUTF("Client 1 : " + text_chat_one.trim());
                    dataOutputStream2.flush();

                    /* sending client one message to client three */
                    dataOutputStream3.writeUTF("Client 1 : " + text_chat_one.trim());
                    dataOutputStream3.flush();
                }

                /* closing server socket */
                serverSocket.close();

                /* closing input and output streams */
                dataOutputStream.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException ignored) {
            }

        }).start();

        /* client two thread */
        new Thread(() -> {
            try {
                serverSocket_client2 = new ServerSocket(port);
                accept2 = serverSocket_client2.accept();
                textArea.appendText("\nClient 2 Connected..");

                /* accepting input and output streams */
                dataInputStream2 = new DataInputStream(accept2.getInputStream());
                dataOutputStream2 = new DataOutputStream(accept2.getOutputStream());

                while (!message2.equals("exit")) {
                    message2 = dataInputStream2.readUTF();
                    textArea.appendText("\nClient 2 : " + message2);

                    dataOutputStream2.writeUTF("Client 2 : " + message2.trim());
                    dataOutputStream2.flush();

                    text_chat_two = message2;

                    /* sending client two message to client two */
                    dataOutputStream.writeUTF("Client 2 : " + text_chat_two.trim());
                    dataOutputStream.flush();

                    /* sending client two message to client three */
                    dataOutputStream3.writeUTF("Client 2 : " + text_chat_two.trim());
                    dataOutputStream3.flush();
                }

                /* closing server socket */
                serverSocket_client2.close();

                /* closing input and output streams */
                dataOutputStream.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException ignored) {
            }

        }).start();


        //        Client three thread
        new Thread(() -> {
            try {
                serverSocket_client3 = new ServerSocket(port3);
                accept3 = serverSocket_client3.accept();
                textArea.appendText("\nClient 3 Connected..");

                /* accepting input and output streams */
                dataInputStream3 = new DataInputStream(accept3.getInputStream());
                dataOutputStream3 = new DataOutputStream(accept3.getOutputStream());

                while (!message3.equals("exit")) {

                    message3 = dataInputStream3.readUTF();
                    textArea.appendText("\nClient 3 : " + message3);

                    dataOutputStream3.writeUTF("Client 3 : " + message3.trim());
                    dataOutputStream3.flush();

                    text_chat_three = message3;

                    /* sending client three message to client three */
                    dataOutputStream.writeUTF("Client 3 : " + text_chat_three.trim());
                    dataOutputStream.flush();

                    /* sending client three message to client three */
                    dataOutputStream2.writeUTF("Client 3 : " + text_chat_three.trim());
                    dataOutputStream2.flush();

                }

                /* closing server socket */
                serverSocket_client3.close();

                /* closing input and output streams */
                dataOutputStream.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException ignored) {
            }

        }).start();
    }


    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF("Server : " + textMessage.getText().trim());
        dataOutputStream.flush();
        dataOutputStream2.writeUTF("Server : " + textMessage.getText().trim());
        dataOutputStream2.flush();
        dataOutputStream3.writeUTF("Server : " + textMessage.getText().trim());
        dataOutputStream3.flush();
    }
}
