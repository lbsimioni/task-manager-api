package br.com.alura.server;

import br.com.alura.server.commands.CommandC1;
import br.com.alura.server.commands.CommandC2BDAccess;
import br.com.alura.server.commands.CommandC2CallWS;
import br.com.alura.server.queues.CommandQueue;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.*;

public class DistributeTask implements Runnable {
    private final ExecutorService threadPool;
    private final TaskServer taskServer;
    private final Socket socket;

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


                switch (command.trim().toLowerCase()) {
                    case "c1": {
                        printStream.println("Command c1 confirmed");
                        this.threadPool.execute(new CommandC1(printStream));
                        break;
                    }
                    case "c2": {
                        printStream.println("Command c2 confirmed");
//                        FutureTask<String> futureTaskWS = new FutureTask<>(new CommandC2CallWS(printStream));
//                        new Thread(futureTaskWS).start();
//                        String result = futureTaskWS.get();

                        Future<String> futureWS = this.threadPool.submit(new CommandC2CallWS(printStream));
                        Future<String> futureBD = this.threadPool.submit(new CommandC2BDAccess(printStream));

                        this.threadPool.submit(() -> {
                            System.out.println("--- Server waiting result for c2 command ---");

                            try {
                                String magicNumberWS = futureWS.get(20, TimeUnit.SECONDS);
                                String magicNumberBD = futureBD.get(20, TimeUnit.SECONDS);

                                printStream.println("--- Command c2 (WS) result: " + magicNumberWS + " ---");
                                printStream.println("--- Command c2 (BD) result: " + magicNumberBD + " ---");
                            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                                futureWS.cancel(Boolean.TRUE);
                                futureBD.cancel(Boolean.TRUE);
                                System.out.println("--- Command c2 failed ----");
                                e.printStackTrace();
                            }

                            System.out.println("--- Server executed c2 command ---");
                        });
                        break;
                    }
                    case "add in queue: command": {
                        System.out.println("--- Server adding command in Queue ---");
                        printStream.println("--- Server adding command in Queue ---");

                        CommandQueue.put(command.replace("add in queue: ", ""));
                        break;
                    }
                    case "shutdown": {
                        System.out.println("--- Server Turning off ---");
                        printStream.println("--- Server Turning off ---");
                        this.taskServer.stop();
                        break;
                    }
                    case "exception": {
                        throw new RuntimeException("Send Exception");
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
