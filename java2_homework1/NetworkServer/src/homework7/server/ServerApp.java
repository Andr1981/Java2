package homework7.server;

import java.io.IOException;
import java.util.logging.*;

public class ServerApp {

    private static final int DEFAULT_PORT = 8189;
    private static final Logger LOGGER_SERVER = Logger.getLogger(ServerApp.class.getPackage().getName());
    private static final Logger LOGGER = Logger.getLogger(ServerApp.class.getName());

    public static void main(String[] args) {
        try {
            Handler h = new FileHandler("log_server.log", true);
            h.setLevel(Level.WARNING);
            h.setFormatter(new SimpleFormatter());
            LOGGER_SERVER.addHandler(h);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int port = getServerPort(args);
        new NetworkServer(port).start();
    }

    private static int getServerPort(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Некорректный формат порта, будет использоваться порт по умолчанию", e);
            }
        }
        return port;
    }
}
