package Services;

import entities.Book;
import entities.Users;


import java.io.*;
import java.util.*;


public class LibraryServiceImpl implements LibrayServices {

    List<Book> books = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book updateBook(int index, Book book) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("books.tst"))){

        }catch (IOException e){

        }
        books.set(index, book);
        return book;
    }

    @Override
    public void deleteBook(String bookName) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getTitle().equals(bookName)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book removed: " + bookName);
        } else {
            System.out.println("Book not found: " + bookName);
        }
    }


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
    public boolean issueBook(String bookName, Users user) {
        Book newBook = null;
        for (Book book : books) {
            if (book.getTitle().equals(bookName)) {
                newBook = book;
                break;
            }
        }


        if (newBook == null) {
            System.out.println("Book not found: " + bookName);
            return false;
        }

        if (newBook.getQuantity() == 0) {
            System.out.println("Book is out of Stock!");
            return false;
        } else {

            Map<String, List<String>> issuedList = newBook.getIssuedList();
            if (issuedList == null) {
                issuedList = new HashMap<>();
            }

            List<String> issuedToUsers = issuedList.getOrDefault(bookName, new ArrayList<>());

            if (issuedToUsers.contains(user.getUsername())) {
                System.out.println("User already issued this book.");
                return false;
            }

            issuedToUsers.add(user.getUsername());
            issuedList.put(bookName, issuedToUsers);
            newBook.setIssuedList(issuedList);
            newBook.setQuantity(newBook.getQuantity() - 1);

            System.out.println("Book Issued: " + newBook.getTitle() + " to user: " + user.getUsername());
            return true;
        }
    }


    @Override
    public boolean returnBook(String bookName, Users user) {
        for (Book book : books) {
            if (book.getTitle().equals(bookName)) {
                Map<String, List<String>> issuedList = book.getIssuedList();
                if (issuedList != null && issuedList.containsKey(bookName)) {
                    List<String> issuedUsers = issuedList.get(bookName);
                    if (issuedUsers.contains(user.getUsername())) {
                        issuedUsers.remove(user.getUsername());

                        if (issuedUsers.isEmpty()) {
                            issuedList.remove(bookName);
                        } else {
                            issuedList.put(bookName, issuedUsers);
                        }

                        book.setQuantity(book.getQuantity() + 1);
                        book.setIssuedList(issuedList);
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    @Override
    public String displayAllBooks() {
        System.out.println("Books List: ");
        try {
            FileReader fileReader = new FileReader("log.data");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (Book book : books) {
//            System.out.println("BookId: " + book.getBookId() + " Title: " + book.getTitle() + " Quantity: " + book.getQuantity() + " Author: " + book.getAuthor());
//        }
        return null;
    }

    public void displayIssuedBook() {
        boolean anyIssued = false;
        System.out.println("Issued books list:");

        for (Book book : books) {
            Map<String, List<String>> issuedList = book.getIssuedList();
            if (issuedList != null && !issuedList.isEmpty()) {
                anyIssued = true;
                for (Map.Entry<String, List<String>> entry : issuedList.entrySet()) {
                    String bookName = entry.getKey();
                    List<String> issueDetails = entry.getValue();
                    System.out.println("Books Name:-" + bookName + " UserName:-" + issueDetails);
                }
            }
        }

        if (!anyIssued) {
            System.out.println("No books have been issued.");
        }
    }

    @Override
    public List<Book> deleteBooks(String booksName, int booksId) {
        return List.of();
    }

//    @Override
//    public List<Book> deleteBooks(String booksName, int booksId) {
//        boolean found = false;
//        for(int i =0;i<books.size();i++){
//            if(books.){
//                System.out.println(i);
//            }
//        }
//        return List.of();
//    }

    @Override
    public boolean searchBook(String bookName, int booksId) {
        boolean found = false;
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(bookName) && book.getBookId() == booksId){
                book.displayBooks();
            }
        }
        if(!found){
            System.out.println("Book name and books Id is not found!!");
        }
        return true;
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