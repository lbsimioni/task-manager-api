package br.com.alura.server.commands;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2CallWS implements Callable<String> {
    private final PrintStream printStream;

    public CommandC2CallWS(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public String call() throws Exception  {
        this.printStream.println("--- Server Calling Web Service for command c2 ---");

        Thread.sleep(10000);

        int number = new Random().nextInt(100) + 1;

        this.printStream.println("--- Server Called Web Service for command c2 ---");

        return Integer.toString(number);
    }
}
