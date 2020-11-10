package at.api.model;

/**
 * Класс с объектами
 * username имя пользователя
 * password пароль
 * используется при Логине
 * и Регистрации в тестах
 */
public class Credentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
