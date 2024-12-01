package project.libraryserver.Book;

import java.util.ArrayList;
import java.util.Comparator;

public class BookSort {
    public static void SortByIdAsc(ArrayList<Book> books) {
        books.sort(Comparator.comparing(Book::getId));
    }

    public static void SortByIdDesc(ArrayList<Book> books) {
        books.sort(Comparator.comparing(Book::getId).reversed());
    }

    public static void SortByTitleAsc(ArrayList<Book> books) {
        books.sort(Comparator.comparing(Book::getTitle));
    }

    public static void SortByTitleDesc(ArrayList<Book> books) {
        books.sort(Comparator.comparing(Book::getTitle).reversed());
    }
}
