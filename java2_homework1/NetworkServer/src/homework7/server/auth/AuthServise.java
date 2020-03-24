package homework7.server.auth;

public interface AuthServise {
    String getUsernameByLoginAndPassword(String login, String password);

    void start();

    void stop();
}
