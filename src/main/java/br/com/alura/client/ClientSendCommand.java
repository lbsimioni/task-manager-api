package br.com.alura.client;

import java.io.PrintStream;
import java.util.Scanner;

public class ClientSendCommand implements Runnable {
    private final PrintStream printStream;
    private final Scanner scanner;

    public ClientSendCommand(PrintStream printStream, Scanner scanner) {
        this.printStream = printStream;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println("--- Client Sending Data ---");
        while (this.scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.trim().equals("")) {
                break;
            }

            this.printStream.println(line);
        }

        this.scanner.close();
        System.out.println("--- Client Stop to Sending Data ---");
    }
}
