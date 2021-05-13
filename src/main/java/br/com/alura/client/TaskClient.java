package br.com.alura.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient implements Runnable {

    private static void start() throws IOException, InterruptedException {


        Socket socket = new Socket("localhost", 12345);
        System.out.println("[CLIENT] Client Connected");

        PrintStream printStreamClient = new PrintStream(socket.getOutputStream());

        Thread sendCommandThread = new Thread(new ClientSendCommand(printStreamClient, new Scanner(System.in)));
        sendCommandThread.start();

        new Thread(new ClientReceiveCommand(new Scanner(socket.getInputStream()))).start();

        sendCommandThread.join();
        printStreamClient.close();
        socket.close();
    }

    @Override
    public void run() {
        try {
            System.out.println("[CLIENT] Starting Client");
            TaskClient.start();
            System.out.println("[CLIENT] Client Stopped");
        } catch (Exception e) {
            System.out.println("----------------------------------");
            System.out.println("[CLIENT] Client Application failed");
            e.printStackTrace();
        }
    }
}
