package Services;

import entities.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadBooks {
    public static List<Book> readBooks(){
        List<Book> books = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("books.txt"))){
            String Line;
            while ((Line = br.readLine()) != null){
                books.add(Book.fromFileString(Line));
            }
        }catch (IOException e){
            System.out.println("Error Message:- "+e.getMessage());
        }
         return books;
    }
}
