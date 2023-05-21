package bookLibrary;
import org.example.bookLibrary.Book;
import org.example.bookLibrary.Borrower;
import org.example.bookLibrary.Library;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.PriorityQueue;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library library = new Library();

    @Test
    void testAddBookToLibrary() {
        Book book1 = new Book("Java Programming", "John Smith");
        library.addBook(book1, 1);
        assertTrue(library.isBookAvailable(book1));
    }

    @Test
    public void testBookTakenWhenCopyNotAvailable() {
        // Add a book to the library
        Book book = new Book("Java Programming", "John Smith");
        library.addBook(book, 1);

        // Borrow the book from the library
        Borrower borrower = new Borrower("Student 1", false, false);
        String result = library.borrowBook(book, borrower);

        // Assert that the book is taken
        assertEquals("Java Programming taken by Student 1", result);
    }

    @Test
    void isBookAvailable() {

        Book book2 = new Book("Data Structures", "Jane Smith");
        Book book3 = new Book("Design Patterns", "Michael Johnson");
        library.addBook(book2, 1);

        assertTrue(library.isBookAvailable(book2));
        // Borrow the book from the library
        Borrower borrower = new Borrower("Student 1", false, false);
        String result = library.borrowBook(book2, borrower);
        assertFalse(library.isBookAvailable(book2));
    }

    @Test
    void testPriorityQueueForBorrowingBook() {
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
        // Add books to the library
        Book book1 = new Book("Mathematics", "Jane Doe");
        Book book2 = new Book("Physics", "John Smith");
        Book book3 = new Book("English Literature", "Alice Johnson");
        library.addBook(book1, 2);
        library.addBook(book2, 1);
        library.addBook(book3, 1);

        // Borrow requests from different users
        Borrower studentA = new Borrower("StudentA", false, false);
        bookRequestQueue.offer(studentA);
        Borrower studentB = new Borrower("StudentB", false, false);
        bookRequestQueue.offer(studentB);
        Borrower seniorStudent = new Borrower("Senior student", false, true);
        bookRequestQueue.offer(seniorStudent);
        Borrower teacher = new Borrower("Teacher", true, false);
        bookRequestQueue.offer(teacher);

        // Borrow books in different order
        String result1 = library.borrowBook(book1, bookRequestQueue.poll());
        String result2 = library.borrowBook(book2, bookRequestQueue.poll());
        String result3 = library.borrowBook(book1, bookRequestQueue.poll());
        String result4 = library.borrowBook(book3, bookRequestQueue.poll());

        // Assert the borrowing order
        assertEquals("Mathematics taken by Teacher", result1); // Student A borrows Mathematics
        assertEquals("Physics taken by Senior student", result2); // Senior Student borrows Physics
        assertEquals("Mathematics taken by StudentA", result3); // Student B borrows Mathematics (after Student A)
        assertEquals("English Literature taken by StudentB", result4); // Teacher borrows English Literature (before Student B)
    }
}
