package Services;

import entities.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogImplementImp implements Logimplement {
    @Override
    public void deleteLog(String bookName) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        String timestamp = date.format(formatter);
        String datalog = new String("Delete book Name "+bookName.toUpperCase()+" at "+timestamp);
        files.WriteLog(datalog);
    }

    FileHandlingServices files = new FileHandlingServices();
    @Override
    public void AddLog(String userName,String bookName){
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = data.format(formatter);
        String logData = new String(userName.toUpperCase() + " added the book: "+bookName+" at "+timestamp);
files.WriteLog(logData);
    }

    @Override
    public void issueLog(String bookName,String userName,String LibraryEmployeeName) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = date.format(formatter);
        String logData = new String(LibraryEmployeeName+ " issued the book: "+bookName+ " to "+userName+ " at "+timestamp);
        files.WriteLog(logData);
    }

    @Override
    public void returnLog(String userName,String bookName) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        String timestamp = date.format(formatter);
        String logData = new String(userName+" returned the book "+bookName+" at "+timestamp);
        files.WriteLog(logData);
    }

    @Override
    public void DisplayAllBookLog(String username) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = date.format(formatter);
        String logdata = username + " viewed all books" + " at " + timestamp;
        files.WriteLog(logdata);
    }

    @Override
    public void updateLog(String oldBookName, String newBookName, int bookId) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = date.format(formatter);
        String logData = "Updated book from \"" + oldBookName.toUpperCase() + "\" to \"" + newBookName.toUpperCase() + "\" (Book ID: " + bookId + ") at " + timestamp;
        files.WriteLog(logData);
    }

    @Override
    public void displayIssuedBookLog() {
        try(BufferedReader br = new BufferedReader(new FileReader("log.data"))){
            boolean found = false;
            String line;
            while ((line = br.readLine()) != null){
                if(line.toUpperCase().contains("Issued")){
                System.out.println(line);
                found = true;
                }
            }
            if(!found){
                System.out.println("Issued Book is not found!!");
            }
            br.close();
        }catch (IOException e){
            System.out.println("Message:- "+e.getMessage());
        }
    }

    @Override
    public void showLog() {

    }
}
