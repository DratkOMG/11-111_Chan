package com.example.controllers.multi;

import com.example.constans.BasicKittens;
import com.example.gameplay.DeckOfCards;
import com.example.sockets.multi.Server2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController2 implements Initializable {

    private Server2 server;

    private static DeckOfCards deckOfCards;
    @FXML
    public ScrollPane spPlayers;
    @FXML
    public TextField amountOfPlayer;
    @FXML
    public Button playButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BasicKittens basicKittens = new BasicKittens();
        try {
            deckOfCards = DeckOfCards.builder()
                    .deck(basicKittens.getCardOfBasicKittens())
                    .backOfCard(new Image(new FileInputStream("src/main/resources/DoneCards/Back Of Card.png")))
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        deckOfCards.shuffle();

        try {
            server = new Server2(new ServerSocket(27), deckOfCards);
            server.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
