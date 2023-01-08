package com.example.sockets.multi;

import com.example.gameplay.DeckOfCards;
import com.example.gameplay.MyDeck;
import com.example.models.Card;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String check;
        while (socket.isConnected()) {
            try {
                synchronized (this) {
                    check = bufferedReader.readLine();
                    System.out.println("Message from clientHandler: " + check);
                    if (check.equals("chat")) {
                        String msgFromChat = bufferedReader.readLine();
                        broadcastMessage(check, msgFromChat);
                    }

                    if (check.equals("update deck")) {
                        DeckOfCards deckOfCards = DeckOfCards.deckFromString(bufferedReader.readLine());
                        updateCommonDeckForEveryone(deckOfCards);
                    }

                    if (check.equals("next turn")) {
                        setNextTurn();
                    }

                    if (check.equals("remove")) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }

                    if (check.equals("bom huhu")) {
                        Boolean withoutExploding = Boolean.valueOf(bufferedReader.readLine());
                        updateExplodingForThem(withoutExploding);
                    }

                    if (check.startsWith("show name")) {
                        String name = bufferedReader.readLine();
                        showMyNameForEveryone(check, name);
                    }

                    if (check.startsWith("set deck")) {
                        MyDeck myDeckForEveryone = MyDeck.myDeckFromString(bufferedReader.readLine());
                        updateMyDeckForEveryone(check, myDeckForEveryone);
                    }

                    if (check.equals("update cards played")) {
                        DeckOfCards deckOfCards = DeckOfCards.deckFromString(bufferedReader.readLine());
                        updateListCardPlayed(deckOfCards);
                    }

                    if (check.equals("update list player")) {
                        String username = bufferedReader.readLine();
                        updateListPlayer(check, username);
                    }

                    if (check.equals("remove player")) {
                        String username = bufferedReader.readLine();
                        updateListPlayer(check, username);
                    }

                    if (check.equals("use favor")) {
                        String username = bufferedReader.readLine();
                        useFavorToPlayer(check, username);
                    }

                    if (check.equals("use attack")) {
                        useAttackToPlayer(check);
                    }

                    if (check.equals("give card")) {
                        Card card = Card.fromString(bufferedReader.readLine());
                        String toPlayer = bufferedReader.readLine();
                        giveCardToPlayer(check, card, toPlayer);
                    }
                    if (check.equals("reverse turn")) {
                        reverseTurn();
                    }

                    System.out.println("-------------");
                }

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void broadcastMessage(String check, String msgFromChat) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(check);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.write(msgFromChat);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void reverseTurn() {
        Collections.reverse(clientHandlers);
    }

    private void useAttackToPlayer(String check) {
        for (int i = 0; i < clientHandlers.size(); i++) {
            if (clientHandlers.get(i).equals(this)) {
                try {
                    clientHandlers.get((i + 1) % clientHandlers.size()).bufferedWriter.write(check);
                    clientHandlers.get((i + 1) % clientHandlers.size()).bufferedWriter.newLine();
                    clientHandlers.get((i + 1) % clientHandlers.size()).bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    private void giveCardToPlayer(String check, Card card, String toPlayer) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.clientUsername.equals(toPlayer)) {
                try {
                    clientHandler.bufferedWriter.write(check);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.write(card.toString());
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void useFavorToPlayer(String check, String username) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.clientUsername.equals(username)) {
                try {
                    clientHandler.bufferedWriter.write(check);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.write(this.clientUsername);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void updateListPlayer(String check, String username) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (!clientHandler.equals(this)) {
                try {
                    clientHandler.bufferedWriter.write(check);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.write(username);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void updateListCardPlayed(DeckOfCards deckOfCards) {
        for (int i = 0; i < clientHandlers.size(); i++) {
            if (!clientHandlers.get(i).equals(this)) {
                try {
                    clientHandlers.get(i).bufferedWriter.write("update cards played");
                    clientHandlers.get(i).bufferedWriter.newLine();
                    clientHandlers.get(i).bufferedWriter.write(DeckOfCards.deckToString(deckOfCards.getDeck()));
                    clientHandlers.get(i).bufferedWriter.newLine();
                    clientHandlers.get(i).bufferedWriter.flush();
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }

    private void showMyNameForEveryone(String check, String name) {
        int tao = 0;
        for (int i = 0; i < clientHandlers.size(); i++) {
            if (clientHandlers.get(i).equals(this)) {
                tao = i;
            }
        }

        ArrayList<ClientHandler> arrayList = ClientHandler.convertList(tao, clientHandlers);
        int a = clientHandlers.size() - 2;
        for (int i = 1; i < arrayList.size(); i++) {
            try {
                arrayList.get(i).bufferedWriter.write(check);
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.write(String.valueOf(a));
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.write(name);
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.flush();
                a--;
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void updateMyDeckForEveryone(String check, MyDeck myDeckForEveryone) {
        int tao = 0;
        for (int i = 0; i < clientHandlers.size(); i++) {
            if (clientHandlers.get(i).equals(this)) {
                tao = i;
            }
        }

        ArrayList<ClientHandler> arrayList = ClientHandler.convertList(tao, clientHandlers);

        int a = clientHandlers.size() - 2;

        for (int i = 1; i < arrayList.size(); i++) {
            try {
                arrayList.get(i).bufferedWriter.write(check);
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.write(String.valueOf(a));
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.write(MyDeck.myDeckToString(myDeckForEveryone));
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.flush();
                a--;
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void updateExplodingForThem(Boolean withoutExploding) {
        int tao = 0;
        for (int i = 0; i < clientHandlers.size(); i++) {
            if (clientHandlers.get(i).equals(this)) {
                tao = i;
            }
        }

        ArrayList<ClientHandler> arrayList = ClientHandler.convertList(tao, clientHandlers);

        int a = clientHandlers.size() - 2;
        for (int i = 1; i < arrayList.size(); i++) {
            try {
                arrayList.get(i).bufferedWriter.write("bom huhu");
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.write(String.valueOf(a));
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.write(String.valueOf(withoutExploding));
                arrayList.get(i).bufferedWriter.newLine();
                arrayList.get(i).bufferedWriter.flush();
                a--;
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void updateCommonDeckForEveryone(DeckOfCards deckOfCards) {
        for (int i = 0; i < clientHandlers.size(); i++) {
            try {
                if (!clientHandlers.get(i).equals(this)) {
                    clientHandlers.get(i).bufferedWriter.write("update deck");
                    clientHandlers.get(i).bufferedWriter.newLine();
                    clientHandlers.get(i).bufferedWriter.write(DeckOfCards.deckToString(deckOfCards.getDeck()));
                    clientHandlers.get(i).bufferedWriter.newLine();
                    clientHandlers.get(i).bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }


    public void removeClientHandler() {
        clientHandlers.remove(this);
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
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

    public void setMyDeck(MyDeck myDeck, DeckOfCards deckOfCards) {
        try {
            this.bufferedWriter.write("start");
            this.bufferedWriter.newLine();
            this.bufferedWriter.write(MyDeck.myDeckToString(myDeck));
            this.bufferedWriter.newLine();
            this.bufferedWriter.write(DeckOfCards.deckToString(deckOfCards.getDeck()));
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFirstTurn() {
        for (int i = 0; i < clientHandlers.size(); i++) {
            try {
                clientHandlers.get(i).bufferedWriter.write("first turn");
                clientHandlers.get(i).bufferedWriter.newLine();
                if (i == 0) {
                    clientHandlers.get(i).bufferedWriter.write(String.valueOf(true));
                    clientHandlers.get(i).bufferedWriter.newLine();
                } else {
                    clientHandlers.get(i).bufferedWriter.write(String.valueOf(false));
                    clientHandlers.get(i).bufferedWriter.newLine();
                }

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void setNextTurn() {
        int nextTurn = 0;
        for (int i = 0; i < clientHandlers.size(); i++) {
            if (clientHandlers.get(i).equals(this)) {
                nextTurn = (i + 1) % clientHandlers.size();
            }
        }
        for (int i = 0; i < clientHandlers.size(); i++) {
            try {
                clientHandlers.get(i).bufferedWriter.write("next turn");
                clientHandlers.get(i).bufferedWriter.newLine();
                if (i == nextTurn) {
                    clientHandlers.get(i).bufferedWriter.write(String.valueOf(true));
                    clientHandlers.get(i).bufferedWriter.newLine();
                } else {
                    clientHandlers.get(i).bufferedWriter.write(String.valueOf(false));
                    clientHandlers.get(i).bufferedWriter.newLine();
                }

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public static ArrayList<ClientHandler> convertList(int tao, ArrayList<ClientHandler> arrayList) {
        List<ClientHandler> part1 = arrayList.subList(0, tao);
        List<ClientHandler> part2 = arrayList.subList(tao, arrayList.size());

        ArrayList<ClientHandler> result = new ArrayList<>(part2);
        result.addAll(part1);

        return result;
    }
}
