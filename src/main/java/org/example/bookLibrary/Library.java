package org.example.bookLibrary;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private final Map<Book, Integer> bookCollections;

    public Library(){
        bookCollections = new HashMap<>();
    }

    public void addBook(Book book, int quantity){
        if (bookCollections.containsKey(book)){
            int copies = bookCollections.get(book);
            int total = copies + quantity;
            bookCollections.put(book, total);
        } else {
            bookCollections.put(book, quantity);
        }
    }

    public String borrowBook(Book book, Borrower borrower){
        if (bookCollections.get(book) > 0){
            int copies = bookCollections.get(book);
            int remaining = copies - 1;
            bookCollections.put(book, remaining);
            return book + " taken by " + borrower.getName();
        }
        return "Book is not available";

    }
}
