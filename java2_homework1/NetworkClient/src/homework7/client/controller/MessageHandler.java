package homework7.client.controller;

@FunctionalInterface
public interface MessageHandler {
    void handle(String message);
}
