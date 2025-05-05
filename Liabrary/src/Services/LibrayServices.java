package Services;

import entities.Book;
import entities.Users;

import java.util.List;

public interface LibrayServices {
    public void addBook(Book book);

    public Book updateBook(int index, Book book);

    public void deleteBook(String bookName);

    public Book getBook(String bookName);

    public List<Book> fetchAllBooks();

    public boolean issueBook(String bookName, Users user);

    public boolean returnBook(String bookName, Users user);

    public void displayAllBooks();

    public void displayIssuedBook();


    List<Book> deleteBooks(String booksName,int booksId);

    boolean searchBook(String bookName, int booksId);

    List<Book> deleteBooks(String bookName);
}