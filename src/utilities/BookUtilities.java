package utilities;

import model.Author;
import model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utilities.ConsoleColors.*;
import static utilities.GetValidData.*;

public class BookUtilities {

    public static String formatAsTable(List<List<String>> rows) {

        //get max size of per row
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        //build string format for columns spaces
        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths) {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }

        //convert string format to string
        String format = formatBuilder.toString();

        //return result with special format spacing
        StringBuilder result = new StringBuilder();
        for (List<String> row : rows) {
            result.append(String.format(format, row.toArray(new String[0]))).
                    append("\n-----------------------------------------------------------------------------------------------------------------\n");
        }
        return result.toString();
    }


    public static void printBookList(List<Book> bookList) {

        //list of rows ----> row = list of string
        List<List<String>> rows = new ArrayList<>();
        //header
        List<String> headers = Arrays.asList(" row",
                WHITE_BRIGHT + "| author name" + RESET,
                WHITE_BRIGHT + "| isbn" + RESET,
                WHITE_BRIGHT + "| title" + RESET,
                WHITE_BRIGHT + "| published year" + RESET,
                WHITE_BRIGHT + "| price" + RESET,
                WHITE_BRIGHT + "| sold number" + RESET,
                WHITE_BRIGHT + "| sold total price" + RESET);
        rows.add(headers);

        for (int i = 0; i < bookList.size(); i++) {




            rows.add(Arrays.asList(" " + (i + 1),
                    "| " + WHITE_BRIGHT + bookList.get(i).getAuthor().getFullName() + RESET,
                    "| " + WHITE_BRIGHT + bookList.get(i).getIsbn() + RESET,
                    "| " + WHITE_BRIGHT + bookList.get(i).getTitle() + RESET,
                    "| " + WHITE_BRIGHT + bookList.get(i).getPublishedYear() + RESET,
                    "| " + WHITE_BRIGHT + bookList.get(i).getPrice() + RESET,
                    "| " + WHITE_BRIGHT + bookList.get(i).getSoldNumber() + RESET,
                    "| " + WHITE_BRIGHT + bookList.get(i).getTotalPrice() + RESET
            ));
        }
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
        System.out.println(formatAsTable(rows));
    }

    public static Book getBookInfo(Author author) {
        String title = getValidName("title: ");
        int publishesYear = getValidInt("published year: ");
        double price = getValidDouble("price: ");
        int count = getValidInt("count: ");
        return new Book(title, author, publishesYear, price, count, 0);
    }

}
