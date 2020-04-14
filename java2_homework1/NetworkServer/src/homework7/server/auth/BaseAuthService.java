package homework7.server.auth;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseAuthService implements AuthService {
    private static final Logger LOGGER = Logger.getLogger(BaseAuthService.class.getName());

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
            LOGGER.log(Level.SEVERE, "Ошибка подключения к базе ", e);
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                LOGGER.log(Level.INFO, "Подключения к базе закрыто!");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при работе с базой ", e);
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
            LOGGER.log(Level.SEVERE, "Ошибка подключения к базе!", e);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                LOGGER.log(Level.INFO, "Подключения к базе закрыто!");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при работе с базой", e);
            }
        }
        return false;
    }


    @Override
    public void start() {
        LOGGER.log(Level.WARNING, "Сервис аутентификации запущен");

    }

    @Override
    public void stop() {
        LOGGER.log(Level.WARNING, "Сервис аутентификации остановлен");
    }
}
