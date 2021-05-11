package br.com.alura.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer implements Runnable {
    private void start() throws IOException {

        System.out.println("--- Starting Server ---");
        ServerSocket server = new ServerSocket(12345);
        System.out.println("--- Started Server ---");

        while (true) {
            this.newClient(server);
        }
    }

    private void newClient(ServerSocket server) throws IOException {
        Socket socket = server.accept();
        System.out.println("Accepting new client in port: " + socket.getPort());
    }

    @Override
    public void run() {
        try {
            this.start();
        } catch (Exception e) {
            System.out.println("----------------------------------");
            System.out.println("--- Server Application failed ---");
            e.printStackTrace();
        }

    }
}