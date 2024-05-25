package io.zentae.snake.engine.io;

import io.zentae.snake.engine.movement.Movement;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ProtocolCoordinator {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private static ProtocolCoordinator PROTOCOL_COORDINATOR;

    private final Channel<String> writerChannel;
    private final Channel<String> readerChannel;

    public ProtocolCoordinator(Channel<String> writerChannel, Channel<String> readerChannel) {
        PROTOCOL_COORDINATOR = this;

        this.writerChannel = writerChannel;
        this.readerChannel = readerChannel;
    }

    public void send(Movement movement) {
        try {
            writerChannel.send("Threshold");
            writerChannel.send(movement.getChannelInitial());
            System.out.println("[*] Sent movement " + movement.name() + " (" + movement.getChannelInitial() + ") to the other player ! Waiting for his play...");
        } catch(IOException | ClassNotFoundException exception) {
            System.out.println("[X] An error occured while sending the data " + movement.getChannelInitial() + " through the channel to the other player : " + exception.getMessage());
        }
    }

    public void getOpponentMove(Consumer<Movement> consumer) {
        // use multithreading to avoid blocking the main thread : the Channel class can cause deadlocks on the main thread, which can be problematic.
        this.executorService.execute(() -> {
            try {
                System.out.println("[~] Waiting for the opponent's move...");
                String data = "";
                // await for channel's response.
                while(!data.contains("Threshold")) {
                    // get data.
                    data = this.readerChannel.getNext();
                }
                // get movement.
                Movement movement = Movement.byInitial(this.readerChannel.getNext());
                // if the movement has not been found.
                if(movement == null) {
                    System.out.println("[X] We received the data : " + data + ", which does not match any movement (valid initials : U - L - D - R)");
                    consumer.accept(Movement.NONE);
                    return;
                }
                // accept the data.
                consumer.accept(movement);
            } catch (IOException | ClassNotFoundException exception) {
                System.out.println("[X] An error occured while waiting for the opponent's move... : " + exception);
                consumer.accept(Movement.NONE);
            }
        });
    }

    public void close() {
        this.executorService.shutdown();
    }

    public static boolean isLoaded() {
        return PROTOCOL_COORDINATOR != null;
    }

    public static ProtocolCoordinator loadProtocols(Channel<String> writerChannel, Channel<String> readerChannel) {
        return PROTOCOL_COORDINATOR == null ? new ProtocolCoordinator(writerChannel, readerChannel) : PROTOCOL_COORDINATOR;
    }

    public static ProtocolCoordinator get() {
        if(PROTOCOL_COORDINATOR == null)
            throw new RuntimeException("The protocol coordinator has not been loaded yet !");
        return PROTOCOL_COORDINATOR;
    }
}
