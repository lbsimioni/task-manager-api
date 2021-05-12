package br.com.alura.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTask implements Runnable {
    private TaskServer taskServer;
    private Socket socket;

    public DistributeTask(TaskServer taskServer, Socket socket) {
        this.taskServer = taskServer;
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
                    case "shutdown": {
                        System.out.println("--- Server Turning off ---");
                        printStream.println("--- Server Turning off ---");
                        this.taskServer.stop();
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
