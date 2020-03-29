package homework8.client.command;

public class BroadcastMessageCommand {
    private final String message;

    public BroadcastMessageCommand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
