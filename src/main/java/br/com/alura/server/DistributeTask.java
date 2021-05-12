package br.com.alura.server;

import java.io.IOException;
import java.io.PrintStream;
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

        try(Scanner scanner = new Scanner(this.socket.getInputStream());
            PrintStream printStream = new PrintStream(socket.getOutputStream())){
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                System.out.println("--- Executing Command: [" + command + "] for " + this.socket);

                switch (command) {
                    case "c1": {
                        printStream.println("Command c1 confirmed");
                        break;
                    }
                    case "c2": {
                        printStream.println("Command c2 confirmed");
                        break;
                    }
                    default: {
                        printStream.println("Command not found!");
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
