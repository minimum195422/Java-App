package project.libraryserver.Database;

import javafx.scene.image.Image;
import org.json.JSONObject;
import project.libraryserver.Book.Book;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Consts.SearchType;
import project.libraryserver.Server.ServerLog;
import project.libraryserver.User.User;

import java.sql.*;
import java.util.*;

public class MySql {
    private static MySql instance;
    private static Connection connection;

    private MySql() {
        try {
            connection = DriverManager.getConnection(
                    DATA.getJdbcUrl(),
                    DATA.getJdbcUser(),
                    DATA.getJdbcPassword());
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database.", e);
        }
    }

    public static MySql getInstance() {
        if (instance == null) {
            instance = new MySql();
        }
        return instance;
    }

    public ArrayList<String> GetNormalUserBasicInformation(String email) {
        ArrayList<String> returnList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT id, first_name, last_name, email FROM user WHERE email = ?;"
                    );
            preparedStatement.setString(1, email);

            // query action
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                returnList.add(rs.getString(1));
                returnList.add(rs.getString(2));
                returnList.add(rs.getString(3));
                returnList.add(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println(returnList);
        return returnList;
    }

    public ArrayList<String> GetGoogleUserBasicInformation(String email) {
        ArrayList<String> returnList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT id, given_name, family_name, email FROM google WHERE email = ?;"
                    );
            preparedStatement.setString(1, email);

            // query action
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                returnList.add(rs.getString(1));
                returnList.add(rs.getString(2));
                returnList.add(rs.getString(3));
                returnList.add(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return returnList;
    }

    public boolean CheckNormalLogin(String email, String password) throws SQLException {
        // Return false if email or password is empty
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Set prepared statement structure
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT p.password " +
                            "FROM user u join passwords p on u.id = p.user_id " +
                            "WHERE u.email = ? and status = 'active';"
                );
        preparedStatement.setString(1, email);

        // query action
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
//            update using BCrypt to hash or check password
//            BCrypt.checkpw(password, rs.getString("password"))

            // compare user given password and password in database
            return password.equals(rs.getString("password"));
        } else {
            System.out.println("Server can not find login information");
            return false;
        }
    }

    public boolean CheckGoogleLogin(String google_id, String email) throws SQLException {
        // Return false if id or email is empty
        if (google_id.isEmpty() || email.isEmpty()) {
            return false;
        }

        // Set prepared statement structure
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT count(*) " +
                            "FROM google " +
                            "WHERE id = ? and email = ?"
                );
        preparedStatement.setString(1, google_id);
        preparedStatement.setString(2, email);

        // query action
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) == 1;
        } else {
            System.out.println("Server can not find login information");
            return false;
        }
    }

    public boolean CreateNewNormalUser(JSONObject json) throws SQLException {

        if (CheckExistEmailOnNormalUser(json.getString("email"))) {
            System.out.println("Email already exists");
            return false;
        }

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO user(first_name, last_name, email, status) "
                            + "VALUES(?, ?, ?, ?)"
                );
        preparedStatement.setString(1, json.getString("first_name"));
        preparedStatement.setString(2, json.getString("last_name"));
        preparedStatement.setString(3, json.getString("email"));
        preparedStatement.setString(4, "active");
        int status = preparedStatement.executeUpdate();
        if (status == 0) {
            System.out.println("Error while inserting user");
            return false;
        }
        preparedStatement.clearBatch();
        preparedStatement = connection.prepareStatement(
                "SELECT id " +
                        "FROM user " +
                        "WHERE email = ?"
        );
        preparedStatement.setString(1, json.getString("email"));
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            int user_id = rs.getInt(1);
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO passwords(user_id, password) " +
                            "VALUES(?, ?)"
            );
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, json.getString("password"));
            status = preparedStatement.executeUpdate();
            if (status == 0) {
                System.out.println("Error while inserting password");
                return false;
            }
        }
        return true;
    }

    public boolean CreateNewGoogleUser(JSONObject json) throws SQLException {
        if (CheckExistEmailOnGoogleUser(json)) {
            System.out.println("Email already exists");
            return false;
        }

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO google (id, given_name, family_name, email, picture_link) "
                                + "VALUES(?, ?, ?, ?, ?)"
                );

        preparedStatement.setString(1, json.getString("id"));
        preparedStatement.setString(2, json.getString("given_name"));
        preparedStatement.setString(3, json.getString("family_name"));
        preparedStatement.setString(4, json.getString("email"));
        preparedStatement.setString(5, json.getString("picture_link"));
        int status = preparedStatement.executeUpdate();
        if (status == 0) {
            System.out.println("Error while inserting user");
            return false;
        }
        return true;
    }

    public boolean CreateUserLinkGoogle(JSONObject json) {
        JSONObject remake = new JSONObject();
        remake.put("first_name", json.getString("given_name"));
        remake.put("last_name", json.getString("family_name"));
        remake.put("email", json.getString("email"));
        remake.put("password", json.getString("id"));
        System.out.println(remake);

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            boolean check = CreateNewNormalUser(remake);
            if (!check) {
                System.out.println("failed at create link google-user");
                return false;
            }

            // get user id
            preparedStatement = connection.prepareStatement("SELECT id FROM user where email = ?");
            preparedStatement.setString(1, json.getString("email"));
            rs = preparedStatement.executeQuery();
            int userID = 0;
            if (rs.next()) {
                userID = rs.getInt(1);
            } else {
                System.out.println("Can't found user id");
                return false;
            }

            // create link
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO user_link_google(user_id, google_id) VALUES (?,?);");
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, json.getString("id"));
            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                System.out.println("Error while inserting user");
                return false;
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

        return true;
    }


    private boolean CheckExistEmailOnNormalUser(String email) throws SQLException {
        String SQL = "SELECT COUNT(*) FROM user WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        boolean existed = false;
        while (rs.next()) {
            if (rs.getInt(1) == 1) existed = true;
        }
        return existed;
    }

    private boolean CheckExistEmailOnGoogleUser(JSONObject json) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT COUNT(*) FROM google "
                            + "WHERE id = ? AND given_name = ? "
                            + "AND family_name = ? AND email = ? and picture_link = ?"
                );
        preparedStatement.setString(1, json.getString("id"));
        preparedStatement.setString(2, json.getString("given_name"));
        preparedStatement.setString(3, json.getString("family_name"));
        preparedStatement.setString(4, json.getString("email"));
        preparedStatement.setString(5, json.getString("picture_link"));
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            if (rs.getInt(1) == 1) return true;
        }
        return false;
    }

    public ArrayList<User> GetAllUser() throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT " +
                                "u.id, " +
                                "u.first_name, " +
                                "u.last_name, " +
                                "u.email, " +
                                "u.status, " +
                                "p.password " +
                            "FROM " +
                                "user u " +
                                "JOIN passwords p ON u.id = p.user_id;"
                );
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            list.add(new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }

        return list;
    }

    public ArrayList<User> GetSearchUserList(String query) throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                "SELECT " +
                        "u.id, " +
                        "u.first_name, " +
                        "u.last_name, " +
                        "u.email, " +
                        "u.status, " +
                        "p.password " +
                    "FROM " +
                        "user u " +
                        "JOIN passwords p ON u.id = p.user_id " +
                    "WHERE " +
                        "u.id = ? " +
                        "OR u.first_name LIKE ? " +
                        "OR u.last_name LIKE ? " +
                        "OR u.email LIKE ?;"
                );
        try {
            // Nếu string có thể chuyển thành số
            int id = Integer.parseInt(query);
            preparedStatement.setInt(1, id);
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển thành số
            preparedStatement.setInt(1, -1);
        }
        preparedStatement.setString(2, "%" + query + "%");
        preparedStatement.setString(3, "%" + query + "%");
        preparedStatement.setString(4, "%" + query + "%");
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            list.add(new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }

        return list;
    }

    public void UpdateUser(
            int user_id,
            String new_firstName,
            String new_lastName,
            String new_email,
            String new_password,
            String new_status
    ) throws SQLException {

        // update bảng passwords trước
        PreparedStatement updatePasswordStatement =
                connection.prepareStatement(
                        "UPDATE passwords SET password = ? WHERE user_id = ?;"
                );
        updatePasswordStatement.setString(1, new_password);
        updatePasswordStatement.setInt(2, user_id);

        if(updatePasswordStatement.executeUpdate() == 0) {
            ServerLog.getInstance().writeLog("Error: Fail to update password table");
            return;
        }

        // sau khi update trên passwords
        // tiến hành update trên user
        PreparedStatement updateUserStatement = connection.prepareStatement(
                "UPDATE user "
                + "SET first_name = ?, last_name = ?, email = ?, status = ? "
                + "WHERE id = ?;"
        );
        updateUserStatement.setString(1, new_firstName);
        updateUserStatement.setString(2, new_lastName);
        updateUserStatement.setString(3, new_email);
        updateUserStatement.setString(4, new_status);
        updateUserStatement.setInt(5, user_id);

        if (updateUserStatement.executeUpdate() == 0) {
            ServerLog.getInstance().writeLog("Error: Fail to update user password table");
        }
    }

    public void DeleteUser(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM passwords WHERE user_id = ?;"
        );
        preparedStatement.setInt(1, id);
        if (preparedStatement.executeUpdate() == 0)
            throw new SQLException("Can not delete from table password");

        preparedStatement.clearBatch();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM user WHERE id = ?;"
        );
        preparedStatement.setInt(1, id);
        if (preparedStatement.executeUpdate() == 0)
            throw new SQLException("Can not delete from table user");
    }

    public boolean AddNewBook(Book book) {
        String bookID = book.getId();
        String title = truncateString(book.getTitle(), 150);
        ArrayList<String> authors = book.getAuthors().isEmpty() ?
                new ArrayList<>(List.of("Unknown")) : book.getAuthors();
        ArrayList<String> categories = book.getCategories().isEmpty() ?
                new ArrayList<>(List.of("Unknown")) : book.getCategories();
        String ISBN_10 = book.getISBN_10().equals("Can't found isbn")
                ? "Unknown" : book.getISBN_10();
        String ISBN_13 = book.getISBN_13().equals("Can't found isbn")
                ? "Unknown" : book.getISBN_13();
        String publisher = truncateString(
                book.getPublisher().equals("Can't found publisher")
                        ? "Unknown" : book.getPublisher(), 100);
        String publishedDate = book.getPublishedDate().equals("Can't found time release")
                ? "Unknown" : book.getPublishedDate();
        String imagePreview = book.getImagePreview() != null
                ? book.getImagePreview().getUrl() : DATA.noImage;
        String description = truncateString(
                book.getDescription().equals("Can't found description")
                        ? "No description" : book.getDescription(), 600);
        String webReaderLink = book.getWebReaderLink();

        try (PreparedStatement checkBookStmt = connection.prepareStatement(
                "SELECT COUNT(*) FROM books WHERE book_id = ?")) {
            // Check if book exits
            checkBookStmt.setString(1, bookID);
            try (ResultSet rs = checkBookStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    ServerLog.getInstance().writeLog("Book already exists in the database.");
                    return false;
                }
            }

            // Thêm sách mới vào database
            try (PreparedStatement insertBookStmt = connection.prepareStatement(
                    "INSERT INTO books(book_id, title, ISBN_10, ISBN_13, publisher, published_date, image_preview, description, web_reader_link) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                insertBookStmt.setString(1, bookID);
                insertBookStmt.setString(2, title);
                insertBookStmt.setString(3, ISBN_10);
                insertBookStmt.setString(4, ISBN_13);
                insertBookStmt.setString(5, publisher);
                insertBookStmt.setString(6, publishedDate);
                insertBookStmt.setString(7, imagePreview);
                insertBookStmt.setString(8, description);
                insertBookStmt.setString(9, webReaderLink);
                int bookInsertStatus = insertBookStmt.executeUpdate();
                if (bookInsertStatus <= 0) {
                    return false;
                }
            }

            // Thêm author
            AddAuthors(authors, bookID);

            // Thêm category
            AddCategories(categories, bookID);

            return true;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    // limit string with number of character
    private static String truncateString(String str, int maxLength) {
        return (str.length() > maxLength) ? str.substring(0, maxLength) : str;
    }

    public boolean UpdateBookInformation(Book book) throws SQLException {
        // change book information
        try (PreparedStatement updateBookStmt = connection.prepareStatement(
                "UPDATE books SET title = ?, publisher = ?, published_date = ?, description = ?, " +
                        "ISBN_10 = ?, ISBN_13 = ?, image_preview = ?, web_reader_link = ? WHERE book_id = ?")) {
            updateBookStmt.setString(1, book.getTitle());
            updateBookStmt.setString(2, book.getPublisher());
            updateBookStmt.setString(3, book.getPublishedDate());
            updateBookStmt.setString(4, book.getDescription());
            updateBookStmt.setString(5, book.getISBN_10());
            updateBookStmt.setString(6, book.getISBN_13());
            updateBookStmt.setString(7, book.getImagePreview().getUrl());
            updateBookStmt.setString(8, book.getWebReaderLink());
            updateBookStmt.setString(9, book.getId());
            updateBookStmt.executeUpdate();
        }

        // ---- update author related to this book ----
        // delete old author-book link
        try (PreparedStatement deleteAuthorsStmt = connection.prepareStatement(
                "DELETE FROM book_authors WHERE book_id = ?")) {
            deleteAuthorsStmt.setString(1, book.getId());
            deleteAuthorsStmt.executeUpdate();
        }
        // update author
        AddAuthors(book.getAuthors(), book.getId());

        // ---- update category related to this book ----
        // delete old category-book link
        try (PreparedStatement deleteCategoryStmt = connection.prepareStatement(
                "DELETE FROM book_categories WHERE book_id = ?")) {
            deleteCategoryStmt.setString(1, book.getId());
            deleteCategoryStmt.executeUpdate();
        }
        // update category
        AddCategories(book.getCategories(), book.getId());

        return true;
    }

    private void AddAuthors(ArrayList<String> authors, String bookID) throws SQLException {
        // add author and link author-book
        for (String author : authors) {
            int authorID;

            // add new author (not exits)
            try (PreparedStatement checkAuthorStmt = connection.prepareStatement(
                    "SELECT author_id FROM authors WHERE name = ?")) {
                checkAuthorStmt.setString(1, truncateString(author, 100));
                try (ResultSet rs = checkAuthorStmt.executeQuery()) {
                    if (rs.next()) {
                        authorID = rs.getInt(1);
                    } else {
                        try (PreparedStatement insertAuthorStmt = connection.prepareStatement(
                                "INSERT INTO authors(name) VALUES(?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                            insertAuthorStmt.setString(1, author);
                            insertAuthorStmt.executeUpdate();
                            try (ResultSet generatedKeys = insertAuthorStmt.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    authorID = generatedKeys.getInt(1);
                                } else {
                                    throw new SQLException("Failed to retrieve author ID.");
                                }
                            }
                        }
                    }
                }
            }

            // Add new link to `book_authors`
            try (PreparedStatement insertBookAuthorStmt = connection.prepareStatement(
                    "INSERT INTO book_authors(author_id, book_id) VALUES(?, ?)")) {
                insertBookAuthorStmt.setInt(1, authorID);
                insertBookAuthorStmt.setString(2, bookID);
                insertBookAuthorStmt.executeUpdate();
            }
        }
    }

    private void AddCategories(ArrayList<String> categories, String bookID) throws SQLException {
        // add category and link category-book
        for (String category : categories) {
            int categoryId;

            // add new category (not exits)
            try (PreparedStatement checkCategoryStmt = connection.prepareStatement(
                    "SELECT category_id FROM categories WHERE category = ?")) {
                checkCategoryStmt.setString(1, truncateString(category, 100));
                try (ResultSet rs = checkCategoryStmt.executeQuery()) {
                    if (rs.next()) {
                        categoryId = rs.getInt(1);
                    } else {
                        try (PreparedStatement insertAuthorStmt = connection.prepareStatement(
                                "INSERT INTO categories(category) VALUES(?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                            insertAuthorStmt.setString(1, category);
                            insertAuthorStmt.executeUpdate();
                            try (ResultSet generatedKeys = insertAuthorStmt.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    categoryId = generatedKeys.getInt(1);
                                } else {
                                    throw new SQLException("Failed to retrieve category ID.");
                                }
                            }
                        }
                    }
                }
            }

            // Add new link to book_categories
            try (PreparedStatement insertBookAuthorStmt = connection.prepareStatement(
                    "INSERT INTO book_categories(category_id, book_id) VALUES(?, ?)")) {
                insertBookAuthorStmt.setInt(1, categoryId);
                insertBookAuthorStmt.setString(2, bookID);
                insertBookAuthorStmt.executeUpdate();
            }
        }
    }

    public void DeleteBook(String bookID) throws SQLException {
        try (PreparedStatement deleteAuthorsStmt = connection.prepareStatement(
                "DELETE FROM book_authors WHERE book_id = ?;")) {
            deleteAuthorsStmt.setString(1, bookID);
            deleteAuthorsStmt.executeUpdate();
        }

        try (PreparedStatement deleteAuthorsStmt = connection.prepareStatement(
                "DELETE FROM book_categories WHERE book_id = ?;")) {
            deleteAuthorsStmt.setString(1, bookID);
            deleteAuthorsStmt.executeUpdate();
        }

        try (PreparedStatement deleteAuthorsStmt = connection.prepareStatement(
                "DELETE FROM books WHERE book_id = ?;")) {
            deleteAuthorsStmt.setString(1, bookID);
            deleteAuthorsStmt.executeUpdate();
        }
    }

    public ArrayList<Book> GetAllDocumentForManage() {
        ArrayList<Book> returnList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT " +
                            "b.book_id, " +
                            "b.title, " +
                            "group_concat(DISTINCT a.name) as 'author_list' " +
                        "FROM " +
                            "books b " +
                            "LEFT JOIN book_authors ba ON b.book_id = ba.book_id " +
                            "LEFT JOIN authors a ON ba.author_id = a.author_id " +
                        "GROUP BY " +
                            "b.book_id"
            );
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                returnList.add(new Book(
                        rs.getString(1), // book id
                        rs.getString(2), // title
                        new ArrayList<>(Arrays.asList(rs.getString(3).split(","))), // authors
                        "0.0", // rate
                        "100" // borrow times
                ));
            }
        } catch (SQLException e) {
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

    private Image GetImageByLink(String link) {
        try {
            return new Image(link);
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Book> GetSearchBookList(String query, SearchType option) {
        ArrayList<Book> returnList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(getPrepareQuery(query, option));

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                returnList.add(new Book(
                        rs.getString(1), // book id
                        rs.getString(2), // title
                        new ArrayList<>(Arrays.asList(rs.getString(3).split(","))), // authors
                        "0.0",
                        "100"
                ));
            }
        } catch (SQLException e) {
            return returnList;
        }
        return returnList;
    }

    private String getPrepareQuery(String query, SearchType option) {
        String setOption = "";
        switch (option) {
            case INID -> setOption = String.format("WHERE b.book_id LIKE '%%%s%%'", query);
            case INTITLE -> setOption = String.format("WHERE b.title LIKE '%%%s%%'", query);
            case INAUTHOR -> setOption = String.format("WHERE a.name LIKE '%%%s%%'", query);
        }
        return String.format("SELECT b.book_id, b.title, group_concat(DISTINCT a.name) as 'author_list' " +
                "FROM books b JOIN book_authors ba ON b.book_id = ba.book_id LEFT JOIN authors a ON ba.author_id = a.author_id " +
                "%s GROUP BY b.book_id", setOption);
    }

    public void AddNewRate(JSONObject json) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO rating(user_id, book_id, rate) values (?, ?, ?);");
            preparedStatement.setInt(1, json.getInt("user_id"));
            preparedStatement.setString(2, json.getString("book_id"));
            preparedStatement.setInt(3, json.getInt("rate"));

            int status = preparedStatement.executeUpdate();
            if (status == 0) {
                System.out.println("Error while inserting user");
                throw new RuntimeException("Fail to update rate");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public String QueryGetReadLinkByBookId(String bookId) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(
            "SELECT web_reader_link FROM books WHERE book_id = ? "
            );
            preparedStatement.setString(1, bookId);
            rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                System.err.println("No book found with ID: " + bookId);
                return null;
            }

            return rs.getString(1);
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

    public boolean QueryChangePassword(int user_id, String password) {
        // change password
        try (PreparedStatement updateBookStmt = connection.prepareStatement(
                "UPDATE passwords SET password = ? WHERE user_id = ?")) {
            updateBookStmt.setString(1, password);
            updateBookStmt.setInt(2, user_id);
            int check = updateBookStmt.executeUpdate();
            if (check == 0) {
                ServerLog.getInstance().writeLog("Can't change user password");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
