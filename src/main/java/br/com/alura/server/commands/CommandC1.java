package br.com.alura.server.commands;

import java.io.PrintStream;

public class CommandC1 implements Runnable {
    private PrintStream printStream;

    public CommandC1(PrintStream printStream) {

        this.printStream = printStream;
    }

    @Override
    public void run() {
        this.printStream.println("--- Server Executing command c1 ---");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.printStream.println("--- Server Executed command c1 ---");
    }
}
