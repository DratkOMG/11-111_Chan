package com.example.demo2;

import com.example.sockets.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private Button btn_send;
    @FXML
    private TextField tf_mess;
    @FXML
    private VBox vb_mess;
    @FXML
    private ScrollPane sp_main;

    private Client client;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            client = new Client(new Socket("localhost", 7));
        } catch (IOException e) {
            e.printStackTrace();
        }

        vb_mess.heightProperty().addListener((observableValue, numberOldValue, numberNewValue) -> {
            sp_main.setVvalue((Double) numberNewValue);
        });

        client.receiveMessFromServer(vb_mess);

        btn_send.setOnAction(event -> {
            String messToSend = tf_mess.getText();
            if (!messToSend.isEmpty()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                Text text = new Text(messToSend);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-color: rgb(239, 242, 255);" +
                        "-fx-background-color: rgb(15,125,242);" +
                        "-fx-background-radius: 20px");
                textFlow.setPadding(new Insets(5, 10, 5, 10));
                text.setFill(Color.color(0.934, 0.945, 0.996));

                hBox.getChildren().add(textFlow);
                vb_mess.getChildren().add(hBox);

                client.sendMessToServer(messToSend);
                tf_mess.clear();
            }
        });
    }

    public static void addLabel(String messFromServer, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(messFromServer);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> {
            vBox.getChildren().add(hBox);
        });
    }
}
