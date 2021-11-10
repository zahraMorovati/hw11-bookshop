package dao;

import exception.DaoConnectionFailed;
import exception.author.AuthorNotFoundException;
import exception.author.AuthorNotSavedException;
import exception.book.BookNotFoundException;
import exception.book.BookNotSavedException;
import model.Author;
import model.Book;
import model.enumation.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dao.AuthorDao.setAuthorInfo;

public class BookDao extends Dao{

    public BookDao() throws ClassNotFoundException, SQLException {

        super();
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "book", null);
            if (!tables.next()) {
                createBookTable();
            }
        }
    }

    private void createBookTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE book (" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    title VARCHAR(25)," +
                "    isbn VARCHAR(25) ," +
                "    author_id INT ," +
                "    published_year INT ," +
                "    price DOUBLE ," +
                "    count INT ," +
                "    sold_number INT ," +
                "    PRIMARY KEY (id),"+
                " FOREIGN KEY (author_id)  REFERENCES author(id))");
    }

    public int save(Book b) throws SQLException, AuthorNotSavedException, BookNotSavedException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO book" +
                            " (title,isbn,author_id,published_year,price,count,sold_number) " +
                            "VALUES ('%s','%s','%d','%d','%f','%d','%d')",
                    b.getTitle(),b.getIsbn(),b.getAuthor().getId(),b.getPublishedYear(),b.getPrice(),b.getCount(),b.getSoldNumber());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            throw new BookNotSavedException("book not saved!");
        }
    }

    public List<Book> findAllBooks() throws SQLException {
        if (getConnection() != null) {
            List<Book> bookList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from book " +
                    "inner join author where author.id=book.author_id");
            while (resultSet.next()) {
                Author author=new Author();
                Book book=new Book();
                setBookInfo(book,resultSet,author);
                bookList.add(book);
            }
            return bookList;
        } else {
            return Collections.emptyList();
        }
    }

    public static void setBookInfo(Book book, ResultSet resultSet,Author author) throws SQLException {
        book.setId(resultSet.getInt("book.id"));
        book.setTitle(resultSet.getString("title"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPublishedYear(resultSet.getInt("published_year"));
        book.setPrice(resultSet.getDouble("price"));
        book.setCount(resultSet.getInt("count"));
        book.setSoldNumber(resultSet.getInt("sold_number"));
        author.setId(resultSet.getInt("author.id"));
        setAuthorInfo(author,resultSet);
        book.setAuthor(author);
    }

    public int addBook(int bookID,int count) throws SQLException, BookNotSavedException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("update book set count=count+'%d'" +
                            "where id='%d'",count,bookID);
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            throw new BookNotSavedException("cannot add book!");
        }
    }

    public int purchaseBook(int bookID,int count) throws SQLException, BookNotSavedException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("update book set sold_number=book.sold_number+'%d' , count=count-'%d' where id='%d'",count,count,bookID);
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            throw new BookNotSavedException("cannot add book!");
        }
    }

    public  Book findBookByID(int id) throws BookNotFoundException, DaoConnectionFailed, SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("select * from book inner join author" +
                    " where author.id=book.author_id and book.id='%d'",id);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Author author=new Author();
                Book book=new Book();
                setBookInfo(book,resultSet,author);
                return book;
            }
            throw new BookNotFoundException("cannot find book!");

        } else {
            throw new DaoConnectionFailed("cannot connect to dataBase!");
        }
    }



}
