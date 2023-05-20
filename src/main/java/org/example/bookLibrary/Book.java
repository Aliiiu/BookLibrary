package org.example.bookLibrary;

public class Book {
    private final String title;
    private final String author;
    private int copies;
    private boolean isTeacherRequest;
    private boolean isSeniorStudentRequest;

    public Book (String title, String author){
        this.author = author;
        this.title = title;
//        this.copies = copies;
        this.isTeacherRequest = false;
        this.isSeniorStudentRequest = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void addBook(){
        this.copies += 1;
    }

    public void removeBook(){
        if (this.copies == 0){
            this.copies -= 1;
        }
    }

    public boolean isTeacherRequest(){
        return isTeacherRequest;
    }

    public void setTeacherRequest(boolean teacherRequest) {
        isTeacherRequest = teacherRequest;
    }

    public void setSeniorStudentRequest(boolean seniorStudentRequest) {
        isSeniorStudentRequest = seniorStudentRequest;
    }

    public boolean isSeniorStudentRequest(){
        return isSeniorStudentRequest;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
