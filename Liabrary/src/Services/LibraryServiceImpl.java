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
    public boolean updateBookById(int id, Book newBook) {
        List<String> lines = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && Integer.parseInt(parts[0]) == id) {
                    lines.add(newBook.toString());
                    updated = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }

        return updated;
    }

//    @Override
//    public void deleteBook(String bookName, int bookId) {
//        // Step 1: Remove from memory
//        Book bookToRemove = null;
//        for (Book book : books) {
//            if (book.getTitle().equalsIgnoreCase(bookName) && book.getBookId() == bookId) {
//                bookToRemove = book;
//                break;
//            }
//        }
//
//        if (bookToRemove != null) {
//            books.remove(bookToRemove);
//            System.out.println("Book removed from memory: " + bookName);
//        } else {
//            System.out.println("Book not found in memory: " + bookName);
//        }
//
//        List<Book> updatedBooks = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 4) {
//                    try {
//                        int id = Integer.parseInt(parts[0].split(":")[1].trim());
//                        String title = parts[1].split(":")[1].trim();
//                        String author = parts[2].split(":")[1].trim();
//                        int quantity = Integer.parseInt(parts[3].split(":")[1].trim());
//
//                        if (!(title.equalsIgnoreCase(bookName) && id == bookId)) {
//                            updatedBooks.add(new Book(title, author, id, quantity));
//                        }
//                    } catch (NumberFormatException e) {
//                        System.out.println("Skipping invalid line: " + line);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//            return;
//        }
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
//            for (Book book : updatedBooks) {
//                bw.write(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getQuantity());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Error writing to file: " + e.getMessage());
//        }
//
//        System.out.println("Book successfully deleted from file (if existed): " + bookName);
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
    public boolean issueBook(int bookId, Users user) {
        Book newBook = null;
        for (Book book : books) {
            if (book != null && book.getBookId() == bookId) {
                newBook = book;
                break;
            }
        }

        if (newBook == null) {
            System.out.println("Book not found with ID: " + bookId);
            return false;
        }

        if (newBook.getQuantity() == 0) {
            System.out.println("Book is out of stock!");
            return false;
        } else {
            Map<String, List<String>> issuedList = newBook.getIssuedList();
            if (issuedList == null) {
                issuedList = new HashMap<>();
            }

            List<String> issuedToUsers = issuedList.getOrDefault(bookId, new ArrayList<>());

            if (issuedToUsers.contains(user.getUsername())) {
                System.out.println("User already issued this book.");
                return false;
            }

            issuedToUsers.add(user.getUsername());
            issuedList.put(String.valueOf(bookId), issuedToUsers);
            newBook.setIssuedList(issuedList);
            newBook.setQuantity(newBook.getQuantity() - 1);

            System.out.println("Book Issued: " + newBook.getTitle() + " (ID: " + bookId + ") to user: " + user.getUsername());
            return true;
        }
    }
    @Override
    public boolean returnBook(int bookId, Users user) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                Map<String, List<String>> issuedList = book.getIssuedList();
                String bookIdStr = String.valueOf(bookId);

                if (issuedList != null && issuedList.containsKey(bookIdStr)) {
                    List<String> issuedUsers = issuedList.get(bookIdStr);

                    if (issuedUsers.contains(user.getUsername())) {
                        issuedUsers.remove(user.getUsername());

                        if (issuedUsers.isEmpty()) {
                            issuedList.remove(bookIdStr);
                        }

                        book.setQuantity(book.getQuantity() + 1);
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
            FileReader fileReader = new FileReader("books.txt");
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
//        return List.of();
//    }

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
                found = true;
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