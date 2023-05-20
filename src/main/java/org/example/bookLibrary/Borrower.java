package org.example.bookLibrary;

public class Borrower {
    private final String name;
    private final Boolean isTeacher;
    private final Boolean isSenior;

    public Borrower(String name, Boolean isTeacher, Boolean isSenior){
        this.name = name;
        this.isTeacher = isTeacher;
        this.isSenior = isSenior;
    }

    public String getName() {
        return name;
    }

    public Boolean isTeacher() {
        return isTeacher;
    }

    public Boolean isSenior() {
        return isSenior;
    }
}
