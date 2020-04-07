package homework7.server.auth;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class BaseAuthService implements AuthService {

    @Override
    public String getUserNameByLoginAndPassword(String login, String password) {

        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:usersChat.db");
            PreparedStatement statement = connection.prepareStatement("SELECT username FROM logins where login = ? and password = ?;");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("username");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Ошибка подключения к базе!");
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                System.out.println("Подключения к базе закрыто!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean changeNickname(String login, String nickname) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:usersChat.db");
            PreparedStatement statement = connection.prepareStatement("UPDATE logins SET username = ? WHERE login = ?;");
            statement.setString(1, nickname);
            statement.setString(2, login);
            if (statement.executeUpdate() != 0) return true;
        } catch (Exception e) {
            System.out.println("Ошибка подключения к базе!");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                System.out.println("Подключения к базе закрыто!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");

    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }
}
