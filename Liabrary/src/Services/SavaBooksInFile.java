package Services;

import entities.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SavaBooksInFile {
    public void saveBooks(Book book){
        try (BufferedWriter read = new BufferedWriter(new FileWriter("books.txt",true))){
            read.write(book.toFileString());
            read.newLine();
        }catch (IOException e){
            System.out.println("Error Message:- "+e.getMessage());
        }
    }
}
