package br.com.alura.server.commands;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2BDAccess implements Callable<String> {
    private final PrintStream printStream;

    public CommandC2BDAccess(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public String call() throws Exception  {
        this.printStream.println("Server Calling BD Access for Command c2");

        Thread.sleep(5000);
        
        int number = new Random().nextInt(100) + 1;

        this.printStream.println("Server Called BD Access for Command c2");

        return Integer.toString(number);
    }
}
