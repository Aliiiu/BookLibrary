package org.example;
import org.example.bookLibrary.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Comparator<Borrower> borrowerComparator = (b1, b2) -> {
            if (b1.isTeacher() && !b2.isTeacher()) {
                return -1; // Teacher has higher priority
            } else if (!b1.isTeacher() && b2.isTeacher()) {
                return 1; // Teacher has higher priority
            } else if (b1.isSenior() && !b2.isSenior()) {
                return -1; // Senior student has higher priority
            } else if (!b1.isSenior() && b2.isSenior()) {
                return 1; // Senior student has higher priority
            } else {
                return 0; // Equal priority (based on arrival order)
            }
        };

        PriorityQueue<Borrower> bookRequestQueue = new PriorityQueue<>(borrowerComparator);
        Book book1 = new Book("Java Programming", "John Doe");
        Book book2 = new Book("Data Structures", "Jane Smith");
        Book book3 = new Book("Design Patterns", "Michael Johnson");

        library.addBook(book1, 3);
        library.addBook(book2, 2);
        library.addBook(book3, 1);
        library.addBook(book3, 1);

        Borrower student1 = new Borrower("Student 1", false, false);
        bookRequestQueue.offer(student1);
        Borrower student2 = new Borrower("Student 2", false, true);
        bookRequestQueue.offer(student2);
        Borrower teacher1 = new Borrower("Teacher 1", true, false);
        bookRequestQueue.offer(teacher1);

        library.borrowBook(book3, bookRequestQueue.poll());
        library.borrowBook(book3, bookRequestQueue.poll());
        library.borrowBook(book3, bookRequestQueue.poll());
    }
}