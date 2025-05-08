import Services.*;
import entities.Book;
import entities.Users;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LogImplementImp logger = new LogImplementImp();
        System.out.println("""
                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                |                                                                                                    |
                ================================ Welcome to my Library Management System =============================
                |                                                                                                    |
                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                """);

        boolean quit = true;
        LibrayServices newLibrary = new LibraryServiceImpl();
        System.out.println("Enter the Library Staff Name: ");
        String StaffName = scanner.nextLine();
        while (quit) {
            try {
                System.out.println("""
                        ===    1. Add a new book.
                        ===    2. Update book.
                        ===    3. Display All Books.
                        ===    4. Issue book.
                        ===    5. Show all issued books.
                        ===    6. Return book.
                        ===    7. Delete a book.
                        ===    8. Search a Books.
                        ===    9. Log show.
                        ===    10. Exit the program.
                        """);
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter bookId: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter book name: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();

                        System.out.print("Enter quantity of the book: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();

                        Book book = new Book(title, author, bookId, quantity);
                        newLibrary.addBook(book);
                        logger.AddLog(StaffName,book.getTitle());
                        BooksManagement.saveBook(book);
                        System.out.println("Book added successfully!\n");
                        break;

                    case 2:
                        try {
                            System.out.print("Enter the Book ID to update: ");
                            int booksId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter new book title: ");
                            String newTitle = scanner.nextLine();

                            System.out.print("Enter new author name: ");
                            String newAuthor = scanner.nextLine();

                            System.out.print("Enter new quantity: ");
                            int newQuantity = scanner.nextInt();
                            scanner.nextLine();

                            Book updatedBook = new Book(newTitle, newAuthor, booksId, newQuantity);

                            boolean isUpdated = BooksManagement.updateBookById(booksId, updatedBook);

                            if (isUpdated) {
                                logger.updateLog("OldData", newTitle, booksId);
                                System.out.println("Book updated successfully.");
                            } else {
                                System.out.println("Book with ID " + booksId + " not found.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error updating the book: " + e.getMessage());
                        }
                        break;

                    case 3:
                        newLibrary.displayAllBooks();
                        logger.DisplayAllBookLog(StaffName);
                        break;

                    case 4:
                        System.out.print("Enter book name to issue: ");
                        String bookName = scanner.nextLine();

                        System.out.print("Enter user's name: ");
                        String userNames = scanner.nextLine();

                        Users newUser = new Users(StaffName);
                        boolean issued = newLibrary.issueBook(bookName, newUser);
                        logger.issueLog(bookName,userNames,StaffName);

                        if (issued) {
                            System.out.println("Book issued successfully to " + StaffName);
                        } else {
                            System.out.println("Book not found or already issued.");
                        }
                        break;

                    case 5:
                        newLibrary.displayIssuedBook();
                        break;

                    case 6:
                        System.out.print("Enter the return Book name: ");
                        String booksName = scanner.nextLine();

                        System.out.print("Enter the user name: ");
                        String returnUsername = scanner.nextLine();

                        Users returnUser = new Users(returnUsername);
                        if (newLibrary.returnBook(booksName, returnUser)) {
                            System.out.println("Book returned successfully.");
                        } else {
                            System.out.println("Book was not issued!");
                        }
                        logger.returnLog(returnUsername,booksName);
                        break;

                    case 7:
                        System.out.print("Enter book name to delete: ");
                        String bookToDelete = scanner.nextLine();
                        System.out.print("Enter the Book ID: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();
                        logger.deleteLog(bookToDelete);
                        newLibrary.deleteBook(bookToDelete, deleteId);
//                        BooksManagement.deleteBookById(deleteId);
                        System.out.println("Book deleted successfully.");
                        break;

                    case 8:
                        System.out.print("Enter the Book Name to Search: ");
                        String searchBookName = scanner.nextLine();
                        System.out.print("Enter the Book Id to Search: ");
                        int searchBookId = scanner.nextInt();
                        newLibrary.searchBook(searchBookName,searchBookId);
                        break;
                    case 9:
                        System.out.println("Show all details of the library system: ");
                        logger.showLog();
                        break;
                    case 10:
                        quit = false;
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number from 1 to 8 — not a string!");
                scanner.nextLine();
            }
        }
    }
}
