package entities;

import java.util.List;
import java.util.Map;

public class Book {
    private String title;
    private String author;
    private int bookId;
    private int quantity;
    private Map<String, List<String>> issuedList;
    private boolean isIssued;

    public Book() {}

    public Book(String title, String author, int bookId, int quantity) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.quantity = quantity;
    }


    public Map<String, List<String>> getIssuedList() {
        return issuedList;
    }

    public void setIssuedList(Map<String, List<String>> issuedList) {
        this.issuedList = issuedList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }

    public static Book fromFileString(String line) {
        try {
            String[] parts = line.split(", ");
            int bookId = Integer.parseInt(parts[0].split(": ")[1].trim());
            String title = parts[1].split(": ")[1].trim();
            String author = parts[2].split(": ")[1].trim();
            int quantity = Integer.parseInt(parts[3].split(": ")[1].trim());

            return new Book(title, author, bookId, quantity);
        } catch (Exception e) {
            System.out.println("Error parsing book from line: " + line);
            return null;
        }
    }

    public String toFileString() {
        return "BookId: " + bookId +
                ", Title: " + title +
                ", Author: " + author +
                ", Quantity: " + quantity;
    }
    public void displayBooks(){
        System.out.println("Book Id: "+getBookId());
        System.out.println("Book Name: "+getTitle());
        System.out.println("Book Author: "+getAuthor());
        System.out.println("Book quantity: "+getQuantity());
    }


}