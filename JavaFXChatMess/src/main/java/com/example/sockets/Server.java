package com.example.sockets;

import com.example.demo2.ServerController;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.socket = serverSocket.accept();
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }
    public void sendMessToClient(String messToClient) {
        try {
            bufferedWriter.write(messToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiveMessFromClient(VBox vBox) {
        new Thread(() -> {
            while (socket.isConnected()) {
                String messFromClient = null;
                try {
                    messFromClient = bufferedReader.readLine();
                    ServerController.addLabel(messFromClient, vBox);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

}
