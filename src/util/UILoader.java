package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UILoader {
    public static void SetUi(String location, AnchorPane allLoaderContext) throws IOException {
        allLoaderContext.getChildren().clear();
        allLoaderContext.getChildren().add(FXMLLoader.load(UILoader.class.getResource("../view/" + location + ".fxml")));
    }

    public static void LogOutHandler(String path, AnchorPane MainContext) throws IOException {
        Stage window = (Stage) MainContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(UILoader.class.getResource("../View/" + path + ".fxml"))));
    }

    public static void SetUiCloseUnderTheAnchorPane(String Owner, AnchorPane FirstPageAnchorPaneContext) throws IOException {
        FirstPageAnchorPaneContext.getChildren().clear();
        FirstPageAnchorPaneContext.getChildren().add(FXMLLoader.load(UILoader.class.getResource("../view/" + Owner + ".fxml")));
    }

    public static void AdminORCashierUI(String pathToUI, AnchorPane OwnerUnderCashier) throws IOException {
        OwnerUnderCashier.getChildren().clear();
        Stage window = (Stage) OwnerUnderCashier.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(UILoader.class.getResource("../view/" + pathToUI + ".fxml"))));
    }
}
