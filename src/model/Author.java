package model;

import model.enumation.Gender;

import java.util.List;

public class Author {

    private int id;
    private String name;
    private String family;
    private Gender gender;
    private int birthYear;
    private String email;
    private List<Book> bookList;

    public Author() {
    }

    public Author(String name, String family, Gender gender, int birthYear, String email) {
        this.name = name;
        this.family = family;
        this.gender=gender;
        this.birthYear = birthYear;
        this.email = email;
    }
    public Author(int id, String name, String family, Gender gender, int birthYear, String email) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.gender=gender;
        this.birthYear = birthYear;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getFullName(){
        return name+" "+family;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", gender=" + gender +
                ", birthYear=" + birthYear +
                ", email='" + email + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
