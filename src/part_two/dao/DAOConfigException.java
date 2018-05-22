package part_two.dao;

public class DAOConfigException extends RuntimeException {
    public DAOConfigException(String message) {
        super(message);
    }

    public DAOConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
