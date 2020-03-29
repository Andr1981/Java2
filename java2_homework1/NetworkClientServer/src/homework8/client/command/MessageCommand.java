package homework8.client.command;

public class MessageCommand {
    private final String username;
    private final String message;

    public MessageCommand(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
}
