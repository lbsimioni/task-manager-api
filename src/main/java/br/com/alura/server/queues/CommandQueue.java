package br.com.alura.server.queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CommandQueue {
    private static BlockingQueue<String> queue;

    public static void init() {
        if (CommandQueue.queue == null)
            new CommandQueue();
    }

    private CommandQueue() {
        CommandQueue.queue = new ArrayBlockingQueue<>(3);
    }

    public static void put(String element) throws InterruptedException {
        CommandQueue.queue.put(element);
    }

    public static String take() throws InterruptedException {
        return CommandQueue.queue.take();
    }
}
