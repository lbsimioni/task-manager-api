package br.com.alura.client;

import java.io.IOException;
import java.net.Socket;

public class TaskClient {
    public static void main(String[] args) {
        try {
            TaskClient.start();
        } catch (Exception e) {
            System.out.println("----------------------------------");
            System.out.println("--- Client Application failed ---");
            e.printStackTrace();
        }
    }

    private static void start() throws IOException {

        System.out.println("--- Starting Client ---");

        Socket socket = new Socket("localhost", 12345);

        System.out.println("--- Client Connected ---");

        socket.close();
    }

}
