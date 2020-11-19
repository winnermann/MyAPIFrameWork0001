package at.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigJDBC3 {
    private static final Logger logger = LoggerFactory.getLogger(ConfigJDBC3.class);

    private ConfigJDBC3() {
    }

    private static Properties configJDBC3 = new Properties();

    static {
        try {
            configJDBC3.load(ConfigJDBC3.class.getResourceAsStream("/configJDBC3.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getJDBCDefaultUsername(){
        return configJDBC3.getProperty("jdbc.username");
    }

    public static String getJDBCDefaultPassword(){
        return configJDBC3.getProperty("jdbc.password");
    }

    public static String getJDBCHost(){
        return configJDBC3.getProperty("jdbc.host");
    }

    public static int getJDBCPort(){
        return Integer.parseInt(configJDBC3.getProperty("jdbc.port"));
    }

    public static String getJDBCPath(){
        return configJDBC3.getProperty("jdbc.servicename");
    }
}
