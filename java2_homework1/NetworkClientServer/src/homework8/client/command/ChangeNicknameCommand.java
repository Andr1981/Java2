package homework8.client.command;

public class ChangeNicknameCommand {
    private final String login;
    private  String username;

    public ChangeNicknameCommand(String login, String username) {
        this.login = login;
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
