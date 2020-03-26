package homework7.server.auth;

import java.util.List;
import java.util.Objects;

public class BaseAuthService implements AuthService {

    private static class UserData {
        private String login;
        private String password;
        private String username;

        public UserData(String login, String password, String username) {
            this.login = login;
            this.password = password;
            this.username = username;
        }

    }

    private static final List<UserData> USER_DATA = List.of(
            new UserData("log1", "pass1", "user1"),
            new UserData("log2", "pass2", "user2"),
            new UserData("log3", "pass3", "user3")

    );

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (UserData userDatum : USER_DATA) {
            if (userDatum.login.equals(login) && userDatum.password.equals(password)) {
                return userDatum.username;
            }
        }
        return null;
    }

    @Override
    public void start() {
        System.out.println("Сервер аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервер аутентификации остановлен");

    }
}
