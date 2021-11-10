package service;

import dao.AuthorDao;
import exception.DaoConnectionFailed;
import exception.author.AuthorNotFoundException;
import exception.author.AuthorNotSavedException;
import model.Author;
import utilities.GetValidData;

import java.sql.SQLException;

import java.util.List;

import static utilities.ConsoleColors.*;

public class AuthorService {

    AuthorDao authorDao=new AuthorDao();
    List<Author> authorList;

    public AuthorService() throws SQLException, ClassNotFoundException {
        authorList=authorDao.findAllAuthors();
    }

    public void printAuthorsIDs() throws SQLException {

        authorList=authorDao.findAllAuthors();
        System.out.println("**** authors list ****");
        for (Author author : authorList) {
            System.out.println("* "+author.getId()+" - "+author.getFullName());
        }
    }

    public Author findAuthorById() throws SQLException {
        authorList=authorDao.findAllAuthors();
        int id= GetValidData.getValidInt("enter author id: ");
        try{
            return authorDao.findAuthorByID(id);
        }catch (AuthorNotFoundException | SQLException | DaoConnectionFailed e) {
            System.out.println(RED+"Exception: "+e.getMessage()+RESET);
        }
        return null;
    }

    public void save(){
        Author newAuthor=GetValidData.getAuthorInfo();
        try {
            authorDao.save(newAuthor);
            System.out.println(GREEN_BRIGHT+"author successfully saved!"+RESET);
        } catch (SQLException | AuthorNotSavedException e) {
            System.out.println(RED+"Exception: "+e.getMessage()+RESET);
        }
    }



}
