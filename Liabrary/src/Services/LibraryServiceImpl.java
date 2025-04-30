package Services;

import entities.Book;
import entities.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryServiceImpl implements LibrayServices {

    List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book updateBook(int index, Book book) {
        books.set(index, book);
        return book;
    }

    @Override
    public void deleteBook(String bookName) {

    }


//    @Override
//    public void deleteBook(String bookName) {
//        Book bookToRemove = null;
//        for (Book book : books) {
//            if (book.getTitle().equals(bookName)) {
//                bookToRemove = book;
//                break;
//            }
//        }
//        if (bookToRemove != null) {
//            books.remove(bookToRemove);
//            System.out.println("Book removed: " + bookName);
//        } else {
//            System.out.println("Book not found: " + bookName);
//        }
//    }


    @Override
    public Book getBook(String bookName) {
        Book book = null;
        for (Book book1 : books) {
            if (book1.getTitle().equals(bookName)) {
                book = book1;
            }
        }
        return book;
    }

    @Override
    public List<Book> fetchAllBooks() {
        return books;
    }

    @Override
    public void issueBook(String bookName, Users user) {
        Book newBook = null;
        for (Book book : books) {
            if (book.getTitle().equals(bookName)) {
                newBook = book;
                break;
            }
        }
        if (newBook == null) {
            System.out.println("Book not found: " + bookName);
        } else {
            Map<String, List<String>> issuedList = new HashMap<>();
            List<String> issuedToUser = new ArrayList<>();
            issuedToUser.add(user.getUsername());
            issuedList.put(newBook.getTitle(), issuedToUser);
            newBook.setIssuedList(issuedList);
            System.out.println("Book Issued: " + newBook.getTitle() + " to user: " + user.getUsername());
        }
    }

    @Override
    public boolean returnBook(String bookName, Users user) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookName)) {
                    if (book.isIssued()) {
                        book.setIssued(false);
                        System.out.println("Book '" + bookName + "' has been successfully returned.");
                    } else {
                        System.out.println("Book '" + bookName + "' was not issued.");
                    }
                    return false;
                }
            }
            System.out.println("Book '" + bookName + "' not found in the library.");

        return false;
    }

    @Override
    public void displayAllBooks() {
        System.out.println("Books List: ");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @Override
    public void displayIssuedBook() {
        System.out.println("Issued books list: ");
        for (Book book : books) {
            Map<String, List<String>> issuedList = book.getIssuedList();
            if (issuedList != null && !issuedList.isEmpty()) {
                System.out.println("issued books list:- ");
                for (Map.Entry<String, List<String>> entry : issuedList.entrySet()) {
                    String bookName = entry.getKey();
                    List<String> issueDetails = entry.getValue();
                    System.out.println(bookName + " : " + issueDetails);
                }
            }
        }
    }

    @Override
    public List<Book> deleteBooks(String bookName) {
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(bookName)) {
                Book removedBook = books.remove(i);
                System.out.println("Removed book: " + removedBook);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found!");
        }

        return books;
    }
}