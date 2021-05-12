package br.com.alura.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTask implements Runnable {
    private Socket socket;

    public DistributeTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("--- Distributing Task for " + this.socket);

        try(Scanner scanner = new Scanner(this.socket.getInputStream())) {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                System.out.println("--- Executing Command: " + command + " for " + this.socket);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
