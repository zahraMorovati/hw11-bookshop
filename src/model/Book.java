package model;

import java.util.Random;
import java.util.UUID;

public class Book {

    private int id;
    private String title;
    private String isbn;
    private Author author;
    private int publishedYear;
    private double price;
    private int count;
    private int soldNumber;
    private double totalPrice;

    public Book() {
    }

    public Book( String title, int publishedYear, double price, int count) {

        this.title = title;
        this.isbn = randomISBN()+"";
        this.publishedYear = publishedYear;
        this.price = price;
        this.count = count;
        this.totalPrice = price * soldNumber;
    }

    public Book(String title, Author author, int publishedYear, double price, int count, int soldNumber) {
        this.title = title;
        this.isbn = randomISBN()+"";
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
        this.count = count;
        this.soldNumber = soldNumber;
        this.totalPrice = price * soldNumber;
    }

    public int getId() {
        return id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public String getAuthorFullName(){
        return author.getFullName();
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
    }

    public double getTotalPrice() {
        return price * soldNumber;
    }

    public void setTotalPrice() {
        this.totalPrice = price * soldNumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + author.toString() +
                ", publishedYear=" + publishedYear +
                ", price=" + price +
                ", count=" + count +
                ", soldNumber=" + soldNumber +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public int randomISBN() {
        int min = 1000000;
        int max = 9999999;
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
