package com.example.sockets.multi;

import com.example.constans.BasicKittens;
import com.example.gameplay.DeckOfCards;
import com.example.gameplay.MyDeck;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server2 {
    private final ServerSocket serverSocket;
    private static DeckOfCards deckOfCards;

    public Server2(ServerSocket serverSocket, DeckOfCards deckOfCards) {
        this.serverSocket = serverSocket;
        this.deckOfCards = deckOfCards;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                MyDeck myDeck = MyDeck.builder()
                        .myCardList(new ArrayList<>())
                        .build();
                myDeck.addCard(deckOfCards.removeDefuseCard());
                myDeck.addAllCard(deckOfCards.removeRandom4Card());

                ClientHandler clientHandler = new ClientHandler(socket);
//                clientHandler.setDeckOfCard(false, deckOfCards, myDeck, true);
                clientHandler.setMyDeck(myDeck, deckOfCards);
                clientHandler.setFirstTurn();
                clientHandler.updateCommonDeckForEveryone(deckOfCards);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server2 server;
        DeckOfCards deckOfCards;
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
