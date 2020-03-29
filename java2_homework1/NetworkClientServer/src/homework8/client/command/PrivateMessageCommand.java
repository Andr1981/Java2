package homework8.client.command;

public class PrivateMessageCommand {
    private final String receiver;
    private final String message;

    public PrivateMessageCommand(String receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}
