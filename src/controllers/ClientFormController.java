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
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController {

    /* emoji unicode holders */
    static String emo1 = "", emo2 = "", emo3 = "";

    /* client one port */
    final int PORT = 5000;

    /* emoji tab */
    public AnchorPane emojiPane;
    public AnchorPane clientOneAnchorPane;

    /* initializing socket */
    Socket accept;

    /* initializing dataInputStream and dataOutputStream */
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    /* initializing String var for holding InputStream and OutputStream value */
    String message = "";

    FileChooser fileChooser = new FileChooser();

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

    /* text message sender - on action */
    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(
                textMessage.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream.flush();
        emojiPane.setVisible(false);
    }

    /* on action for opening emoji window */
    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    /* setting up file chooser for client one */
    public void imageSendOnAction(MouseEvent mouseEvent) throws IOException {
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("ZIP", "*.zip"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("TEXT", "*.txt"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );


        File file = fileChooser.showOpenDialog(new Stage());
        System.out.println(file);
//
//
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Open Resource File");
//            File selectedFile = fileChooser.showOpenDialog(new Stage());

//            if (selectedFile != null) {
//
//                String[] res = selectedFile.getName().split("\\.");
//                BufferedImage finalImage;
//
//                try {
//                    finalImage = ImageIO.read(selectedFile);
//                } catch (Exception e) {
//                    new Alert(Alert.AlertType.NONE, "Please select a file-type associated with images! try again", ButtonType.CLOSE).showAndWait();
//                    return;
//                }
//
//                ByteArrayOutputStream bout = new ByteArrayOutputStream();
//                ImageIO.write(finalImage, res[1], bout);

            /*byte[] temp_payload = dataOutputStream3Name.getBytes(StandardCharsets.UTF_8);
            byte[] temp_header = ByteBuffer.allocate(4).putInt(temp_payload.length).array();*/

//                byte[] payload = bout.toByteArray();
            /*byte[] header = ByteBuffer.allocate(4).putInt(payload.length).array();

            byte[] frame = ArrayUtils.addAll(temp_header,header);

            frame = ArrayUtils.addAll(frame,sender);
            frame = ArrayUtils.addAll(frame,temp_payload);
            frame = ArrayUtils.addAll(frame,payload);*/

//                dataOutputStream3.write(-1);
//                dataOutputStream3.write(payload);
//                dataOutputStream3.flush();
            }


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
