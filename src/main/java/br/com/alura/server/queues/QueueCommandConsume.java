package br.com.alura.server.queues;

public class QueueCommandConsume implements Runnable {

    private static int commandNumber = 1;

    @Override
    public void run() {
        try {
            String command = null;

            while ((command = CommandQueue.take()) != null) {
                int  actualCommandNumber = QueueCommandConsume.commandNumber++;

                System.out.println("--- Consuming " + command + ", number: " + actualCommandNumber + " ---");
                Thread.sleep(5000);
                System.out.println("--- Consumed " + command + ", number: " + actualCommandNumber +  " ---");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
