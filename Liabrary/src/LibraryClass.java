
import Services.LibrayServices;
import Services.LibraryServiceImpl;
import entities.Book;
import entities.Users;

import java.util.Scanner;

public class LibraryClass {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                |                                                                                                    |   
                ================================ Welcome to my Library Management System =============================
                |                                                                                                    |   
                ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                """);

        boolean quit = true;
        LibrayServices newLibrary = new LibraryServiceImpl();
        ;
        while (quit) {
            System.out.println("""
                    ===    1. Add a new book.
                    ===    2. Update book.
                    ===    3. Display All Books.
                    ===    4. Issue book.
                    ===    5. Show all issued books.
                    ===    6. Return book.
                    ===    7. Delete a book.
                    ===    8. Exit the program.
                    """);
            System.out.println("Enter your Choices: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter bookId: ");
                    int bookId = scanner.nextInt();

                    scanner.nextLine();

                    System.out.println("Enter book name: ");
                    String title = scanner.nextLine();

                    System.out.println("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.println("Enter quantity of the book: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    Book book = new Book(title, author, bookId, quantity);
                    newLibrary.addBook(book);
                    System.out.println("Books Add successfully!!");
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Enter bookName to update: ");
                    String bookName = scanner.nextLine();

                    Book updatedBook = newLibrary.getBook(bookName);


                    System.out.println("Enter new book name: ");
                    String newBookName = scanner.nextLine();

                    updatedBook.setTitle(newBookName);
                    System.out.println("Book title updated successfully.");
                    break;

                case 3:
                    newLibrary.displayAllBooks();
                    break;

                case 4:
                    System.out.println("Enter bookName to issue: ");
                    String myBook = scanner.nextLine();
                    scanner.nextLine();

                    System.out.println("Enter the user to issue the book for: ");
                    String userName = scanner.nextLine();
                    Users newUser = new Users(userName);
                    System.out.println("Your book name is: " + myBook);

                    newLibrary.issueBook(myBook, newUser);
                    break;

                case 5:
                    newLibrary.displayIssuedBook();
                    break;

                case 6:
                    System.out.println("Enter the return Book: ");
                    String booksName = scanner.nextLine();
                    System.out.println("Enter the userName: ");
                    String returnUsername = scanner.nextLine();
                    Users returnUser  = new Users(returnUsername);
                    newLibrary.returnBook(booksName,returnUser);
                    break;

                case 7:
                    System.out.println("Book deleted successfully.");
                    break;

                case 8:
                    quit = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }


    }
}
