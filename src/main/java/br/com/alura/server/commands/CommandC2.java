package br.com.alura.server.commands;

import java.io.PrintStream;

public class CommandC2 implements Runnable {
    private PrintStream printStream;

    public CommandC2(PrintStream printStream) {

        this.printStream = printStream;
    }

    @Override
    public void run() {
        this.printStream.println("--- Server Executing command c2 ---");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.printStream.println("--- Server Executed command c2 ---");
    }
}
