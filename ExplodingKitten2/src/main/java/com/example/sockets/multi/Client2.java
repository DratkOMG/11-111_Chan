package com.example.sockets.multi;

import com.example.controllers.multi.ClientController2;
import com.example.gameplay.DeckOfCards;
import com.example.gameplay.MyDeck;
import com.example.models.Card;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client2 {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client2(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    public void listenForMess(Button nextCardBtn, VBox themDeck0, HBox themDeck1, VBox themDeck2,
                              ImageView themExploding0, ImageView themExploding1, ImageView themExploding2,
                              Text playerName0, Text playerName1, Text playerName2, ImageView cardsPlayed,
                              List<String> listPlayer, Text danger, VBox vb_mess) {
        new Thread(() -> {
            String check;
            while (socket.isConnected()) {
                synchronized (this) {
                    try {
                        check = bufferedReader.readLine();
                        System.out.print("Mess from client: ");
                        System.out.println(check);
                        if (check.equals("chat")) {
                            String msgFromChat = bufferedReader.readLine();
                            ClientController2.addLabel(msgFromChat, vb_mess);
                        }

                        if (check.equals("start")) {
                            MyDeck myDeck = MyDeck.myDeckFromString(bufferedReader.readLine());
                            DeckOfCards deckOfCards = DeckOfCards.deckFromString(bufferedReader.readLine());
                            updateMyDeck(myDeck, deckOfCards);
                        }

                        if (check.equals("first turn")) {
                            Boolean btn = Boolean.valueOf(bufferedReader.readLine());
                            nextCardBtn.setVisible(btn);
                        }

                        if (check.equals("update deck")) {
                            DeckOfCards deckOfCards = DeckOfCards.deckFromString(bufferedReader.readLine());
                            updateCommonDeckForEveryone(deckOfCards);
                        }

                        if (check.equals("update cards played")) {
                            DeckOfCards deckOfCards = DeckOfCards.deckFromString(bufferedReader.readLine());
                            updateCardsPlayed(deckOfCards, cardsPlayed);
                        }

                        if (check.equals("update list player")) {
                            String username = bufferedReader.readLine();
                            updateListPlayerForEveryone(username, listPlayer);
                        }

                        if (check.equals("remove player")) {
                            String username = bufferedReader.readLine();
                            removePlayer(username, listPlayer);
                        }

                        if (check.equals("next turn")) {
                            Boolean btn = Boolean.valueOf(bufferedReader.readLine());
                            nextCardBtn.setVisible(btn);
                        }

                        if (check.equals("bom huhu")) {
                            String i = bufferedReader.readLine();
                            Boolean withoutExploding = Boolean.valueOf(bufferedReader.readLine());
                            String themExploding = "themExploding" + i;
                            System.out.println(themExploding);
                            if (themExploding.equals(themExploding0.getId())) {
                                updateExplodingForThem(withoutExploding, themExploding0);
                            } else if (themExploding.equals(themExploding1.getId())) {
                                updateExplodingForThem(withoutExploding, themExploding1);
                            } else {
                                updateExplodingForThem(withoutExploding, themExploding2);
                            }
                        }

                        if (check.startsWith("show name")) {
                            String i = bufferedReader.readLine();
                            String name = bufferedReader.readLine();
                            String playerName = "playerName" + i;
                            System.out.println(playerName);
                            if (playerName.equals(playerName0.getId())) {
                                showMyName(name, playerName0);
                            } else if (playerName.equals(playerName1.getId())) {
                                showMyName(name, playerName1);
                            } else {
                                showMyName(name, playerName2);
                            }
                        }

                        if (check.startsWith("set deck")) {
                            String i = bufferedReader.readLine();
                            MyDeck myDeckForEveryone = MyDeck.myDeckFromString(bufferedReader.readLine());
                            String themDeck = "themDeck" + i;
                            System.out.println(themDeck);
                            if (themDeck.equals(themDeck0.getId())) {
                                updateMyDeckForEveryOneVbox(myDeckForEveryone, themDeck0);
                            } else if (themDeck.equals(themDeck1.getId())) {
                                updateMyDeckForEveryOneHbox(myDeckForEveryone, themDeck1);
                            } else {
                                updateMyDeckForEveryOneVbox(myDeckForEveryone, themDeck2);
                            }
                        }

                        if (check.equals("use favor")) {
                            String username = bufferedReader.readLine();
                            chooseACardForPlayer(username, danger);
                        }

                        if (check.equals("use attack")) {
                            usedAttackToMySelf(danger);
                        }

                        if (check.equals("give card")) {
                            Card card = Card.fromString(bufferedReader.readLine());
                            receiveCard(card);
                        }
                        System.out.println("-------------");

                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    private void usedAttackToMySelf(Text danger) {
        String notification = "used attack to you";
        ClientController2.usedAttackToYou(notification, danger);
    }

    private void removePlayer(String username, List<String> listPlayer) {
        ClientController2.removePlayer(username, listPlayer);
    }

    private void receiveCard(Card card) {
        ClientController2.receiveCard(card);
    }

    private void chooseACardForPlayer(String username, Text danger) {
        String notification = username + " used favor to you";
        ClientController2.usedFavorToYou(username, notification, danger);
    }

    private void updateListPlayerForEveryone(String username, List<String> listPlayer) {
        ClientController2.setListPlayer(username, listPlayer);
    }

    private void updateCardsPlayed(DeckOfCards deckOfCards, ImageView cardsPlayed) {
        ClientController2.setCardsPlayed(deckOfCards, cardsPlayed);
    }

    public void acceptNewClient() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void showMyName(String playerName, Text playerNameText) {
        ClientController2.setPlayerName(playerName, playerNameText);
    }

    private void updateMyDeckForEveryOneHbox(MyDeck myDeckForEveryone, HBox hBoxThemDeck) {
        ClientController2.setThemDeckHBox(myDeckForEveryone, hBoxThemDeck);
    }

    private void updateMyDeckForEveryOneVbox(MyDeck myDeckForEveryone, VBox vBoxThemDeck) {
        ClientController2.setThemDeckVBox(myDeckForEveryone, vBoxThemDeck);
    }

    private void updateExplodingForThem(Boolean withoutExploding, ImageView themExploding) {
        ClientController2.setThemExploding(withoutExploding, themExploding);
    }

    private void updateMyDeck(MyDeck myDeck, DeckOfCards deckOfCards) {
        ClientController2.setMyDeck(myDeck, deckOfCards);
    }

    private void updateCommonDeckForEveryone(DeckOfCards deckOfCards) {
        ClientController2.setDeckOfCard(deckOfCards);
    }

    public void updateCommonDeckForEveryone(String updateDeck, DeckOfCards deckOfCards) {
        try {
            bufferedWriter.write(updateDeck);
            bufferedWriter.newLine();
            bufferedWriter.write(DeckOfCards.deckToString(deckOfCards.getDeck()));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMyExplodingForAnyone(String bomHuhu, boolean withoutExploding) {
        try {
            bufferedWriter.write(bomHuhu);
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(withoutExploding));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufferedWriter.write("remove");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMyDeckForEveryone(String setMyDeckForEveryone, MyDeck myDeck) {
        try {
            bufferedWriter.write(setMyDeckForEveryone);
            bufferedWriter.newLine();
            bufferedWriter.write(MyDeck.myDeckToString(myDeck));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void nextTurn(String nextTurn) {
        try {
            bufferedWriter.write(nextTurn);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMyNameForEveryone(String showMyName, String myName) {
        try {
            bufferedWriter.write(showMyName);
            bufferedWriter.newLine();
            bufferedWriter.write(myName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateListCardPlayed(String updateCardsPlayed, DeckOfCards listCardPlayed) {
        try {
            bufferedWriter.write(updateCardsPlayed);
            bufferedWriter.newLine();
            bufferedWriter.write(DeckOfCards.deckToString(listCardPlayed.getDeck()));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateListPlayer(String updateListPlayer, String username) {
        try {
            bufferedWriter.write(updateListPlayer);
            bufferedWriter.newLine();
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playerUsedFavor(String cardToUse, String choiceBoxValue) {
        try {
            bufferedWriter.write(cardToUse);
            bufferedWriter.newLine();
            bufferedWriter.write(choiceBoxValue);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void giveMyCardToPlayer(String giveCard, Card cardsPlayed, String toPlayer) {
        try {
            bufferedWriter.write(giveCard);
            bufferedWriter.newLine();
            bufferedWriter.write(cardsPlayed.toString());
            bufferedWriter.newLine();
            bufferedWriter.write(toPlayer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePlayer(String removePlayer, String username) {
        try {
            bufferedWriter.write(removePlayer);
            bufferedWriter.newLine();
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playerUsedAttack(String useAttack) {
        try {
            bufferedWriter.write(useAttack);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reverseTurn(String reverseTurn) {
        try {
            bufferedWriter.write(reverseTurn);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMess(String chat, String messToSend) {
        try {
            bufferedWriter.write(chat);
            bufferedWriter.newLine();
            bufferedWriter.write(username + ": " + messToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
}
