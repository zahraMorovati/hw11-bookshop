package utilities;

import model.Author;
import model.Book;
import model.enumation.Gender;

import java.util.List;
import java.util.Scanner;
import static utilities.ConsoleColors.*;

public class GetValidData {

    public static Scanner input = new Scanner(System.in);

    public static String getValidName(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String name = input.next();
        if (name.matches("[a-zA-Z]*")) {
            return name;
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidName(message);
        }
    }

    public static String getValidPhoneNumber(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String phoneNumber = input.next();
        String str = phoneNumber.substring(0);
        if (isNumeric(str)) {
            return phoneNumber;
        } else {
            System.out.println(RED + "invalid phone number!" + RESET);
            return getValidPhoneNumber(message);
        }
    }

    public static int getValidInt(String message) {

        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Integer.parseInt(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidInt(message);
        }
    }



    public static String getValidPersonalCode(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return str;
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidPersonalCode(message);
        }
    }

    public static double getValidDouble(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidDouble(message);
        }
    }

    public static int getValidChoice(String message, int maxChoice) {
        int number = getValidInt(message);
        for (int i = 1; i < maxChoice + 1; i++) {
            if (i == number) {
                return number;
            }
        }
        System.out.println("invalid choice!");
        return getValidChoice(message, maxChoice);
    }

    public static boolean isNumeric(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static Gender getValidGender(){
        int choice = getValidChoice("1)Male 2)Female \nenter your choice: ", 2);
        if(choice==1)
            return Gender.MALE;
        else return Gender.FEMALE;
    }

    public static Author getAuthorInfo(){

         String name=getValidName("name: ");
         String family=getValidName("family: ");
         Gender gender=getValidGender();
         int birthYear=getValidInt("birth year: ");
         String email=getValidName("email: ");

        return new Author(name,family,gender,birthYear,email);
    }

    public static Book getBookInfo(){

         String title=getValidName("title: ");
         int publishedYear=getValidInt("publishedYear: ");
         double price=getValidDouble("price: ");
         int count=getValidInt("count :");
         return new Book(title,publishedYear,price,count);
    }







}
