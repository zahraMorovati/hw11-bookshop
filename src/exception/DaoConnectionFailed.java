package exception;

public class DaoConnectionFailed extends Exception{

    public DaoConnectionFailed(String message) {
        super(message);
    }
}
