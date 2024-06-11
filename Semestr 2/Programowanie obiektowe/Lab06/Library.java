package org.example;

public class Library {
    public static void main(String[] args) {

        Book defaultBook = new Book();
        Book parametrBook = new Book("Symfonia C++", "Jerzy Grebosz", 2000, 2004, 3);
        Book copyBook = new Book(parametrBook);
        System.out.println(" ");

        defaultBook.display();
        parametrBook.display();
        copyBook.display();

        System.out.println("countStatic_ value: " + Book.getCountStatic_());
    }
}

class Book {
    private String title;
    private String author;
    private int year;

    private Edition edition;

    private static int countStatic_ = 0;
    //public int countNonStatic_ = 0;

    //public static int idStatic_ = 0;
    private int idNonStatic_ = 0;

    public Book() {
        title = "ERROR";
        author = "ERROR";
        year = -1;

        edition = new Edition();

        //idNonStatic_ = ++countStatic_;

        System.out.println("Default constructor for Book class.");
    }

    public Book(String title, String author, int year, int publicationYear, int editionNumber) {
        this.title = title;
        this.author = author;
        this.year = year;

        idNonStatic_ = ++countStatic_;

        edition = new Edition(publicationYear, editionNumber);

        System.out.println("Constructor with parameters for Book class.");
    }
    public Book(Book sourceBook) {
        this.title = sourceBook.title;
        this.author = sourceBook.author;
        this.year = sourceBook.year;

        idNonStatic_ = ++countStatic_;

        edition = new Edition(sourceBook.edition);

        System.out.println("Copy constructor for Book class");
    }

    public void display() {
        System.out.println("Displaying values: ID:" + idNonStatic_ + " " + title + " " + author + " " + year);
        System.out.print("Information about edition: " + edition.toString());
        System.out.println("\n");
    }

    public static int getCountStatic_() {
        return countStatic_;
    }

    private class Edition {
        private int publicationYear;
        private int editionNumber;

        public Edition() {
            this.publicationYear = -1;
            this.editionNumber = -1;
        }

        public Edition(int publicationYear, int editionNumber) {
            this.publicationYear = publicationYear;
            this.editionNumber = editionNumber;
        }

        public Edition(Edition anotherEdition) {
            this.publicationYear = anotherEdition.publicationYear;
            this.editionNumber = anotherEdition.editionNumber;
        }

        public String toString() {
            return "Edition: " + editionNumber + " of year: " + publicationYear;
        }
    }

}
