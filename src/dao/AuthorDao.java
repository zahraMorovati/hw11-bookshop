package dao;
import exception.DaoConnectionFailed;
import exception.author.AuthorNotFoundException;
import exception.author.AuthorNotSavedException;
import model.Author;
import model.enumation.Gender;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AuthorDao extends Dao{

    public AuthorDao() throws ClassNotFoundException, SQLException {
        super();
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "author", null);
            if (!tables.next()) {
                createAuthorTable();
            }
        }
    }

    private void createAuthorTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE author (" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    first_name VARCHAR(25)," +
                "    last_name VARCHAR(25)," +
                "    gender VARCHAR(25)," +
                "    birth_year INT," +
                "    email VARCHAR(25) ," +
                "    PRIMARY KEY (id))");
    }

    public int save(Author a) throws SQLException, AuthorNotSavedException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO author" +
                    " (first_name, last_name,gender,birth_year,email) " +
                    "VALUES ('%s','%s','%s','%d','%s')",
                    a.getName(),a.getFamily(),a.getGender().toString(),a.getBirthYear(),a.getEmail());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
           throw new AuthorNotSavedException("author not saved!");
        }
    }

    public List<Author> findAllAuthors() throws SQLException {
        if (getConnection() != null) {
            List<Author> authors = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from author");
            while (resultSet.next()) {
                Author author=new Author();
                author.setId(resultSet.getInt("id"));
                setAuthorInfo(author, resultSet);
                authors.add(author);
            }
            return authors;
        } else {
            return Collections.emptyList();
        }
    }

    public static void setAuthorInfo(Author author, ResultSet resultSet) throws SQLException {
        author.setName(resultSet.getString("first_name"));
        author.setFamily(resultSet.getString("last_name"));
        author.setGender(Gender.valueOf(resultSet.getString("gender")));
        author.setBirthYear(resultSet.getInt("birth_year"));
        author.setEmail(resultSet.getString("email"));
    }

    public Author findAuthorByID(int id) throws SQLException, DaoConnectionFailed, AuthorNotFoundException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery=String.format("select * from author where id='%d'",id);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Author author=new Author();
                author.setId(resultSet.getInt("id"));
                setAuthorInfo(author, resultSet);
                return author;
            }
            throw new AuthorNotFoundException("cannot find author!");

        } else {
           throw new DaoConnectionFailed("cannot connect to dataBase!");
        }
    }





}
