package service;

import dao.BookDao;
import exception.DaoConnectionFailed;
import exception.author.AuthorNotSavedException;
import exception.book.BookNotFoundException;
import exception.book.BookNotSavedException;
import model.Book;
import utilities.GetValidData;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import static utilities.ConsoleColors.*;

public class BookService {
    BookDao bookDao = new BookDao();
    AuthorService authorService=new AuthorService();
    List<Book> bookList;

    public BookService() throws SQLException, ClassNotFoundException {
        bookList = bookDao.findAllBooks();
    }

    public List<Book> sortBookList() throws SQLException {
        List<Book> newBookList= bookDao.findAllBooks();
        newBookList.sort(Comparator.comparing(Book::getAuthorFullName));
        newBookList.sort((o1, o2) -> {
            if (o1.getAuthorFullName().equalsIgnoreCase(o2.getAuthorFullName())) {
                return (Integer.compare(o1.getPublishedYear(), o2.getPublishedYear()));
            }
            return 0;
        });
        return newBookList;
    }

    public void save() throws SQLException {

        Book newBook=GetValidData.getBookInfo();
        authorService.printAuthorsIDs();
        newBook.setAuthor(authorService.findAuthorById());
        try {
            bookDao.save(newBook);
            System.out.println(GREEN_BRIGHT+"book successfully saved!"+RESET);
        } catch (SQLException | AuthorNotSavedException | BookNotSavedException e) {
            System.out.println(RED+"Exception: "+e.getMessage()+RESET);
        }
    }

    public void printBookList() throws SQLException {

        bookList = bookDao.findAllBooks();
        System.out.println("**** book list ****");
        for (Book book : bookList) {
            System.out.println("id: "+book.getId()+"  title: "+book.getTitle()+"  price: "+book.getPrice()+" t"+"  count: "+book.getCount());
        }
    }

    public Book getBookByID() throws SQLException {
        bookList = bookDao.findAllBooks();
        int id= GetValidData.getValidInt("enter book id: ");
        try{
            return bookDao.findBookByID(id);
        }catch (BookNotFoundException | SQLException | DaoConnectionFailed e) {
            System.out.println(RED+"Exception: "+e.getMessage()+RESET);
            return getBookByID();
        }
    }

    public void addBook(Book book ,int count)  {
        try {
            bookDao.addBook(book.getId(),count);
            System.out.println(GREEN_BRIGHT+"book successfully added!"+RESET);
        } catch (BookNotSavedException | SQLException e) {
            System.out.println(RED+"Exception: "+e.getMessage()+RESET);
        }
    }

    public void purchaseBook(Book book ,int count){

        if(book.getCount()>count){
            try {
                bookDao.purchaseBook(book.getId(),count);
                System.out.println(GREEN_BRIGHT+"book successfully purchased!"+RESET);
            } catch (SQLException | BookNotSavedException e) {
                System.out.println(RED+"Exception: "+e.getMessage()+RESET);
            }
        }else {
            System.out.println(RED+"book count is not enough!"+RESET);
        }

    }







}
