package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;

public class ClientFormController {

    public static String userName;

    /* emoji unicode holders */
    static String emo1 = "", emo2 = "", emo3 = "";

    /* client one port */
    final int PORT = 5000;

    /* emoji tab */
    public AnchorPane emojiPane;
    public AnchorPane clientOneAnchorPane;
    public ScrollPane scrollpane;
    public VBox vbox;

    /* initializing socket */
    Socket accept;

    /* initializing dataInputStream and dataOutputStream */
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    /* initializing String var for holding InputStream and OutputStream value */
    String message = "";

    FileChooser fileChooser = new FileChooser();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
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

                /* getting / reading messages from server side */
                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    textArea.appendText(message + "\n");
                }

                /* sending message to server side */
                dataOutputStream.writeUTF(message.trim() + emo1 + emo2 + emo3);
                dataOutputStream.flush();

                accept.close();

                /* closing input and output streams */
                dataOutputStream.close();
                dataInputStream.close();
                textMessage.clear();

            } catch (IOException ignored) {
            }

        }).start();

    }


    /* on action for opening emoji window */
    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    /* setting up file chooser for client one */
    public void imageSendOnAction(MouseEvent mouseEvent) throws IOException, InterruptedException {
        /**
         *
         * fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
         new FileChooser.ExtensionFilter("ZIP", "*.zip"),
         new FileChooser.ExtensionFilter("PDF", "*.pdf"),
         new FileChooser.ExtensionFilter("TEXT", "*.txt"),
         new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
         );

         File file = fileChooser.showOpenDialog(new Stage());
         System.out.println(file);

         BufferedImage image = ImageIO.read(new File(file.toString()));

         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         ImageIO.write(image, fileChooser.toString(), byteArrayOutputStream);

         byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
         dataOutputStream.write(size);
         dataOutputStream.write(byteArrayOutputStream.toByteArray());
         dataOutputStream.flush();
         System.out.println("Flushed: " + System.currentTimeMillis());

         Thread.sleep(30000);
         System.out.println("Closing: " + System.currentTimeMillis());
         dataOutputStream.close();

         */

    }

    /* text message sender - on action */
    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(
                //TODO : remove this
                textMessage.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream.flush();
        emojiPane.setVisible(false);
        textMessage.clear();
    }

    /* emoji pane disabling on-action */
    public void textMessage(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
    }

    /* emoji unicode assigning section */
    public void l1emoOnAction(MouseEvent mouseEvent) {
        textMessage.setText(textMessage.getText() + "\uD83D\uDE42");
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {
        textMessage.setText(textMessage.getText() + "\uD83D\uDE0D");
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
        textMessage.setText(textMessage.getText() + "\uD83E\uDD2A");
    }

    public void setController(AnchorPane anchorPane) {
        this.clientOneAnchorPane = anchorPane;
    }
}
