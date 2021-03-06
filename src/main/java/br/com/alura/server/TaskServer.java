package br.com.alura.server;

import br.com.alura.server.exceptions.ExceptionHandler;
import br.com.alura.server.queues.CommandQueue;
import br.com.alura.server.queues.QueueCommandConsume;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskServer implements Runnable {
    private final ServerSocket serverSocket;
    private final ExecutorService threadPool;
    private final AtomicBoolean isRunning;
    // private volatile boolean isRunning;

    public TaskServer() throws IOException {
        System.out.println("[SERVER] Starting Server");
        this.serverSocket = new ServerSocket(12345);
        this.threadPool = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler(new ExceptionHandler());
            return thread;
        });
        this.isRunning = new AtomicBoolean(true);
        this.startQueue();
        System.out.println("[SERVER] Started Server");
    }

    public void stop() throws IOException {
        this.isRunning.set(false);
        this.serverSocket.close();
        this.threadPool.shutdown();
        System.out.println("[SERVER] Server Stopped");
    }

    private void newClient() throws IOException {
        try {
            Socket socket = this.serverSocket.accept();
            System.out.println("[SERVER] Accepting New Client in Port: " + socket.getPort());

            this.threadPool.execute(new DistributeTask(this.threadPool, this, socket));
        } catch (SocketException e) {}
    }

    private void startQueue() {
        CommandQueue.init();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new QueueCommandConsume());
            thread.setUncaughtExceptionHandler(new ExceptionHandler());
            thread.setDaemon(true);
            thread.start();
        }

    }

    @Override
    public void run() {
        try {
            while (isRunning.get()) {
                this.newClient();
            }
        } catch (Exception e) {
            System.out.println("----------------------------------");
            System.out.println("[SERVER] Server Application Failed");
            e.printStackTrace();
        }

    }
}
