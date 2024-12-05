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

    static class SortByRating implements Comparator<Book> {
        public int compare(Book a, Book b) {
            if (a.getRating() == b.getRating()) {
                if (a.getBorrowed_time() == b.getBorrowed_time()) {
                    return a.getTitle().compareTo(b.getTitle());
                } else {
                    return a.getBorrowed_time() - b.getBorrowed_time();
                }
            } else if (a.getRating() < b.getRating()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    static class SortByBorrowedTime implements Comparator<Book> {
        public int compare(Book a, Book b) {
            if (a.getBorrowed_time() == b.getBorrowed_time()) {
                return a.getTitle().compareTo(b.getTitle());
            } else {
                return a.getBorrowed_time() - b.getBorrowed_time();
            }
        }
    }


    public static void SortByRatingAsc(ArrayList<Book> books) {
        books.sort(new SortByRating());
    }

    public static void SortByRatingDesc(ArrayList<Book> books) {
        books.sort(new SortByRating().reversed());
    }

    public static void SortByBorrowedTimeAsc(ArrayList<Book> books) {
        books.sort(new SortByBorrowedTime());
    }

    public static void SortByBorrowedTimeDesc(ArrayList<Book> books) {
        books.sort(new SortByBorrowedTime().reversed());
    }

}
