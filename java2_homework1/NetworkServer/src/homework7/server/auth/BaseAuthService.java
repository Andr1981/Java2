package homework7.server.auth;

import java.util.List;
import java.util.Objects;

public class BaseAuthService implements AuthServise {

    private static class UserData {
        private String login;
        private String password;
        private String username;

        public UserData(String login, String password, String username) {
            this.login = login;
            this.password = password;
            this.username = username;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserData userData = (UserData) o;
            return Objects.equals(login, userData.login) &&
                    Objects.equals(password, userData.password) &&
                    Objects.equals(username, userData.username);
        }

        @Override
        public int hashCode() {

            return Objects.hash(login, password, username);
        }
    }

    private static final List<UserData> USER_DATA = List.of(
            new UserData("log1","pass1","user1")
            new UserData("log1","pass1","user1")
            new UserData("log1","pass1","user1")
    );

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public void start() {
        System.out.println("Сервер аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервер аутентификации остановле");

    }
}
