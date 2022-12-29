package com.example.sockets;

import com.example.demo2.ClientController;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    public void sendMessToServer(String messToServer) {
        try {
            bufferedWriter.write(messToServer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessFromServer(VBox vb_mess) {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    String messFromServer = bufferedReader.readLine();
                    ClientController.addLabel(messFromServer, vb_mess);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}
