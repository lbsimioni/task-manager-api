package br.com.alura.server.commands;

import java.io.PrintStream;

public class CommandC1 implements Runnable {
    private final PrintStream printStream;

    public CommandC1(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void run() {
        this.printStream.println("Server Executing Command c1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.printStream.println("Server Executed Command c1");
        System.out.println("[SERVER] Server Executed Command c1");
    }
}
