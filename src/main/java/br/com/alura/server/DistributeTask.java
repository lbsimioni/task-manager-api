package br.com.alura.server;

import br.com.alura.server.commands.CommandC1;
import br.com.alura.server.commands.CommandC2;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistributeTask implements Runnable {
    private ExecutorService threadPool;
    private TaskServer taskServer;
    private Socket socket;

    public DistributeTask(ExecutorService threadPool, TaskServer taskServer, Socket socket) {
        this.threadPool = threadPool;
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
                        this.threadPool.execute(new CommandC1(printStream));
                        break;
                    }
                    case "c2": {
                        printStream.println("Command c2 confirmed");
                        this.threadPool.execute(new CommandC2(printStream));
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
