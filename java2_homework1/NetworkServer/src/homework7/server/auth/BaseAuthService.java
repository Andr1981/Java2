package homework7.server.auth;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class BaseAuthService implements AuthService {

    private static Connection connection;
    private static final String USERDATA_DATABASE = "usersChat.db";

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + USERDATA_DATABASE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized String getUsernameByLoginAndPassword(String login, String password) {
        String username = null;
        try {
            connect();
            String sql = "SELECT * FROM usersChat WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.getString(3).equals(password)) {
                username = resultSet.getString(4);
            }
            return username;
        }  catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) {
                    System.out.println("Подключение к базе закрыто !");
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
