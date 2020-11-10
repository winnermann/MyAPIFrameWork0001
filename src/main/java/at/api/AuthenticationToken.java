package at.api;

/**
 * Класс с объектом token, используется при Логине
 * и Регистрации в тестах
 */
public class AuthenticationToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
