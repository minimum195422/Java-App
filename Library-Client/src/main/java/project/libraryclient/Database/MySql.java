package project.libraryclient.Database;

import javafx.scene.image.Image;
import project.libraryclient.Book.Book;
import project.libraryclient.Consts.DATA;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class MySql {
    private static MySql instance;
    private static Connection connection;
    private boolean accessable;


    public MySql() {
        try {
            connection = DriverManager.getConnection(
                    DATA.GetJdbcUrl(),
                    DATA.GetJdbcUser(),
                    DATA.GetJdbcPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.accessable = false;
    }

    public static synchronized MySql getInstance() {
        if (instance == null) {
            instance = new MySql();
        }
        return instance;
    }

    public void SetAccessable(boolean b) {
        this.accessable = b;
    }

    public ArrayList<String> getBookBySubstring(String str) throws SQLException {
        ArrayList<String> bookList = new ArrayList<>();
        String SQL = "SELECT title FROM books WHERE title LIKE '%" + str + "%'";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String name = rs.getString(1);
            bookList.add(name);
//            System.out.println(name);
        }
        return bookList;
    }

    public ArrayList<Book> getBasicInfoOfBook(String name) throws SQLException {
        ArrayList<Book> bookList = new ArrayList<>();
        Book book;
        String SQL = "SELECT books.book_id as id, title, group_concat(authors.name) as author, image_preview "
                + "FROM books "
                + "JOIN book_authors ON books.book_id = book_authors.book_id "
                + "JOIN authors ON authors.author_id = book_authors.author_id "
                + "WHERE title = ? "
                + "GROUP BY books.book_id";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        Image img;
        while (rs.next()) {
            // title, author, image
            String id = rs.getString(1);
            String title = rs.getString(2);
            String[] authorListInString = rs.getString(3).split(",");
            String image = rs.getString(4);

            ArrayList<String> authors = new ArrayList<>(Arrays.asList(authorListInString));
            img = new Image(image);
            book = new Book(id, title, authors, img);
            bookList.add(book);
        }
        return bookList;
    }

    public ArrayList<Book> QueryForBookCard() {
        ArrayList<Book> returnList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT " +
                            "b.book_id, " +
                            "b.title, " +
                            "group_concat(DISTINCT a.name) as 'author_list', " +
                            "b.image_preview " +
                        "FROM " +
                            "books b " +
                            "LEFT JOIN book_authors ba ON b.book_id = ba.book_id " +
                            "LEFT JOIN authors a ON ba.author_id = a.author_id " +
                        "GROUP BY " +
                            "b.book_id " +
                        "LIMIT 3"
            );
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                returnList.add(new Book(
                        rs.getString(1), // book id
                        rs.getString(2), // title
                        new ArrayList<>(Arrays.asList(rs.getString(3).split(","))), // authors
                        GetImageByLink(rs.getString(4)) // cover image
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return returnList;
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return returnList;
    }

    private Image GetImageByLink(String link) {
        try {
            return new Image(link);
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Book> GetSearchBookList(String query) {
        ArrayList<Book> returnList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(
            "SELECT " +
                    "b.book_id, " +
                    "b.title, " +
                    "group_concat(DISTINCT a.name) as 'author_list', " +
                    "b.image_preview " +
                "FROM " +
                    "books b " +
                    "LEFT JOIN book_authors ba ON b.book_id = ba.book_id " +
                    "LEFT JOIN authors a ON ba.author_id = a.author_id " +
                "WHERE " +
                    "b.title LIKE ? "  +
                "GROUP BY " +
                    "b.book_id "
            );
            preparedStatement.setString(1, "%" + query + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                returnList.add(new Book(
                        rs.getString(1), // book id
                        rs.getString(2), // title
                        new ArrayList<>(Arrays.asList(rs.getString(3).split(","))), // authors
                        GetImageByLink(rs.getString(4)) // cover image
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return returnList;
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return returnList;
    }

    public Book GetBookById(String bookId) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(
            "SELECT " +
                    "b.book_id, " +
                    "b.title, " +
                    "group_concat(DISTINCT a.name) as 'author_list', " +
                    "b.publisher, " +
                    "b.published_date, " +
                    "b.description, " +
                    "group_concat(DISTINCT c.category) as 'category_list', " +
                    "b.ISBN_13, " +
                    "b.ISBN_10, " +
                    "b.image_preview, " +
                    "b.web_reader_link " +
                "FROM " +
                    "books b " +
                    "LEFT JOIN book_authors ba ON b.book_id = ba.book_id " +
                    "LEFT JOIN authors a ON ba.author_id = a.author_id " +
                    "LEFT JOIN book_categories bc ON b.book_id = bc.book_id " +
                    "LEFT JOIN categories c ON bc.category_id = c.category_id " +
                "WHERE b.book_id = ? " +
                "GROUP BY b.book_id"
            );
            preparedStatement.setString(1, bookId);
            rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                System.err.println("No book found with ID: " + bookId);
                return null;
            }

            return new Book(
                    rs.getString(1), // book id
                    rs.getString(2), // title
                    new ArrayList<>(Arrays.asList(rs.getString(3).split(","))), // authors
                    rs.getString(4), // publisher
                    rs.getString(5), // published date
                    rs.getString(6), // description
                    new ArrayList<>(Arrays.asList(rs.getString(7).split(","))), // categories
                    rs.getString(8), // ISBN 13
                    rs.getString(9), // ISBN 10
                    GetImageByLink(rs.getString(10)), // Image preview
                    rs.getString(11) // web reader link
            );
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
