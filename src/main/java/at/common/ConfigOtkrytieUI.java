package at.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigOtkrytieUI {
    private static final Logger logger = LoggerFactory.getLogger(ConfigOtkrytieUI.class);

    private ConfigOtkrytieUI() {
    }

    private static Properties configOtkrytieUI = new Properties();

    static {
        try {
            configOtkrytieUI.load(ConfigOtkrytieUI.class.getResourceAsStream("/configOtkrytieUI.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return configOtkrytieUI.getProperty("ui.username");
    }

    public static String getSDCDefaultPassword(){
        return configOtkrytieUI.getProperty("ui.password");
    }

    public static String getOtkrytieUIHost(){
        return configOtkrytieUI.getProperty("ui.host");
    }

    public static int getOtkrytieUIPort(){
        return Integer.parseInt(configOtkrytieUI.getProperty("ui.port"));
    }

    public static String getOtkrytieUIPath(){
        return configOtkrytieUI.getProperty("ui.path");
    }
}
