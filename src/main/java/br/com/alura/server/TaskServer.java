package br.com.alura.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskServer implements Runnable {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private AtomicBoolean isRunning;
    // private volatile boolean isRunning;

    public TaskServer() throws IOException {
        System.out.println("--- Starting Server ---");
        this.serverSocket = new ServerSocket(12345);
        this.threadPool = Executors.newCachedThreadPool();
        this.isRunning = new AtomicBoolean(true);
        System.out.println("--- Started Server ---");
    }

    public void stop() throws IOException {
        this.isRunning.set(false);
        this.serverSocket.close();
        this.threadPool.shutdown();
        System.out.println("--- Server Stopped ---");
    }

    private void newClient() throws IOException {
        try {
            Socket socket = this.serverSocket.accept();
            System.out.println("Accepting new client in port: " + socket.getPort());

            this.threadPool.execute(new DistributeTask(this.threadPool, this, socket));
        } catch (SocketException e) {}
    }

    @Override
    public void run() {
        try {
            while (isRunning.get()) {
                this.newClient();
            }
        } catch (Exception e) {
            System.out.println("----------------------------------");
            System.out.println("--- Server Application failed ---");
            e.printStackTrace();
        }

    }
}
