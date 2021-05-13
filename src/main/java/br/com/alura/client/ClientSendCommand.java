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
        System.out.println("[CLIENT] Client Sending Data");
        while (this.scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.trim().equals("")) {
                break;
            }

            this.printStream.println(line);

            if (line.trim().equals("shutdown")) {
                break;
            }
        }

        this.scanner.close();
        System.out.println("[CLIENT] Client Stop to Sending Data");
    }
}
