package org.example.bookLibrary;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Library {
    private final Map<Book, Integer> bookCollections;
    private static final Logger logger = LogManager.getLogger(Library.class);

    public Library(){
        bookCollections = new HashMap<>();
    }

    public void addBook(Book book, int quantity){
        if (bookCollections.containsKey(book)){
            int copies = bookCollections.get(book);
            int total = copies + quantity;
            bookCollections.put(book, total);
            System.out.println(book.getTitle() + " added to the collection to make it a total of " + bookCollections.get(book));
            logger.info(book.getTitle() + " added to the collection to make it a total of " + bookCollections.get(book));
        } else {
            bookCollections.put(book, quantity);
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " newly added to the library");
            logger.warn(book.getTitle() + " by " + book.getAuthor() + " newly added to the library");
        }
    }

    public String borrowBook(Book book, Borrower borrower){
        if (bookCollections.get(book) > 0){
            int copies = bookCollections.get(book);
            int remaining = copies - 1;
            bookCollections.put(book, remaining);
            System.out.println(book.getTitle() + " taken by " + borrower.getName());
            logger.info(book.getTitle() + " taken by " + borrower.getName());
            return book.getTitle() + " taken by " + borrower.getName();
        } else{
            System.out.println("Book is not available");
            logger.warn("Book is not available");
            return "Book is not available";
        }
    }

    public boolean isBookAvailable(Book book) {
        if (this.bookCollections.containsKey(book)) {
            return this.bookCollections.get(book) > 0;
        }
        return false;
    }
}
