package br.com.alura.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class TaskClient implements Runnable {

    private static void start() throws IOException {

        System.out.println("--- Starting Client ---");

        Socket socket = new Socket("localhost", 12345);

        PrintStream printStreamClient = new PrintStream(socket.getOutputStream());
        printStreamClient.println("c1");

        System.out.println("--- Client Connected ---");

        printStreamClient.close();
        socket.close();
    }

    @Override
    public void run() {
        try {
            TaskClient.start();
        } catch (Exception e) {
            System.out.println("----------------------------------");
            System.out.println("--- Client Application failed ---");
            e.printStackTrace();
        }
    }
}
