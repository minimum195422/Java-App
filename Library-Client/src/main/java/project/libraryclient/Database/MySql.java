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


    public MySql() {
        try {
            connection = DriverManager.getConnection(
                    DATA.GetJdbcUrl(),
                    DATA.GetJdbcUser(),
                    DATA.GetJdbcPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized MySql getInstance() {
        if (instance == null) {
            instance = new MySql();
        }
        return instance;
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

    public Book QueryForBookCardById(String book_id) {
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
                            "b.book_id = ?"
            );
            preparedStatement.setString(1, book_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getString(1), // book id
                        rs.getString(2), // title
                        new ArrayList<>(Arrays.asList(rs.getString(3).split(","))), // authors
                        GetImageByLink(rs.getString(4)) // cover image
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return null;
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
                    "b.image_preview " +
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
                    GetImageByLink(rs.getString(10)) // Image preview
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

    public String QueryGetAvgRating(String bookId) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(
                "select avg(rate) from rating where book_id = ?;"
            );
            preparedStatement.setString(1, bookId);
            rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                return "None";
            }
            return String.format("%.1f", rs.getDouble(1));
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

    public String QueryGetBorrowTime(String bookId) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "select count(book_id) from borrow where book_id = ?;"
            );
            preparedStatement.setString(1, bookId);
            rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                return "None";
            }
            return String.format("%d", rs.getInt(1));
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
