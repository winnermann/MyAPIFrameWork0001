package at.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private Config() {
    }

    private static Properties config = new Properties();

    static {
        try {
            config.load(Config.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return config.getProperty("sdc.username");
    }

    public static String getSDCDefaultPassword(){
        return config.getProperty("sdc.password");
    }

    public static String getSDCHost(){
        return config.getProperty("sdc.host");
    }

    public static int getSDCPort(){
        return Integer.parseInt(config.getProperty("sdc.port"));
    }

    public static String getSDCPath(){
        return config.getProperty("sdc.path");
    }
}
