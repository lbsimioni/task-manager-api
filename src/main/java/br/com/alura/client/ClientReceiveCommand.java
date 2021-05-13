package br.com.alura.client;

import java.util.Scanner;

public class ClientReceiveCommand implements Runnable {
    private final Scanner scanner;

    public ClientReceiveCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println("--- Client Receiving Data From Server ---");

        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            System.out.println(line);
        }

        scanner.close();
        System.out.println("--- Client Stop Receiving Data From Server ---");
    }
}
