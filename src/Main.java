import model.Book;
import service.AuthorService;
import service.BookService;
import utilities.BookUtilities;
import utilities.GetValidData;

import java.sql.SQLException;

import static utilities.ConsoleColors.*;
import static utilities.GetValidData.getValidChoice;


public class Main {

    public static void main(String[] args){
        mainMenu();
    }

    public static void mainMenu()  {

        try {
            BookService bookService = new BookService();
            AuthorService authorService = new AuthorService();
            while (true) {

                System.out.println("----------------- welcome to bookshop management system -----------------");
                int choice = getValidChoice("1)save book\n" +
                        "2)save author\n" +
                        "3)add book\n" +
                        "4)purchase book\n" +
                        "5)print report table\n" +
                        "6)exit\n" +
                        "enter your choice: ", 6);

                switch (choice) {
                    case 1: {
                        bookService.save();
                    }
                    break;
                    case 2: {
                        authorService.save();
                    }
                    break;
                    case 3: {

                        bookService.printBookList();
                        Book book = bookService.getBookByID();
                        int count = GetValidData.getValidInt("count: ");
                        bookService.addBook(book, count);
                    }
                    break;
                    case 4: {
                        bookService.printBookList();
                        Book book = bookService.getBookByID();
                        int count = GetValidData.getValidInt("count: ");
                        bookService.purchaseBook(book, count);
                    }
                    break;
                    case 5: {

                        BookUtilities.printBookList(bookService.sortBookList());
                    }
                    break;
                    case 6: {
                        System.exit(0);
                    }
                    break;

                    default:
                        System.out.println(RED + "Unexpected value: " + choice + RESET);
                }
            }
        } catch (SQLException | ClassNotFoundException | NullPointerException e) {
            System.out.println(RED+"Exception: "+e.getMessage()+RESET);
        }


    }


}
