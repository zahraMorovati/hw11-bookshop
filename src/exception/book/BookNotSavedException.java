package exception.book;

public class BookNotSavedException extends Exception{
    public BookNotSavedException(String message) {
        super(message);
    }
}
