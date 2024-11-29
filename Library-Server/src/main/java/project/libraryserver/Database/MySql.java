package project.libraryserver.Database;

import project.libraryserver.Book.Book;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Server.ServerLog;
import project.libraryserver.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean CreateNewNormalUser(
            String firstName,
            String lastName,
            String email,
            String password) throws SQLException {

        if (CheckExistEmailOnNormalUser(email)) {
            System.out.println("Email already exists");
            return false;
        }


        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO user(first_name, last_name, email, status) "
                            + "VALUES(?, ?, ?, ?)"
                );
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, email);
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
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            int user_id = rs.getInt(1);
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO passwords(user_id, password) " +
                            "VALUES(?, ?)"
            );
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, password);
            status = preparedStatement.executeUpdate();
            if (status == 0) {
                System.out.println("Error while inserting password");
                return false;
            }
        }
        return true;
    }

    public boolean CreateNewGoogleUser(
            String google_id,
            String given_name,
            String family_name,
            String email,
            String picture) throws SQLException {

        if (CheckExistEmailOnGoogleUser(
                google_id, given_name, family_name, email, picture
        )) {
            System.out.println("Email already exists");
            return false;
        }

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO google (id, given_name, family_name, email, picture_link) "
                                + "VALUES(?, ?, ?, ?, ?)"
                );

        preparedStatement.setString(1, google_id);
        preparedStatement.setString(2, given_name);
        preparedStatement.setString(3, family_name);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, picture);

        int status = preparedStatement.executeUpdate();
        if (status == 0) {
            System.out.println("Error while inserting user");
            return false;
        }
        return true;
    }

    public static boolean CheckExistEmailOnNormalUser(String email) throws SQLException {
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

    public static boolean CheckExistEmailOnGoogleUser(
        String google_id,
        String given_name,
        String family_name,
        String email,
        String picture) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT COUNT(*) FROM google "
                            + "WHERE id = ? AND given_name = ? "
                            + "AND family_name = ? AND email = ? and picture_link = ?"
                );
        preparedStatement.setString(1, google_id);
        preparedStatement.setString(2, given_name);
        preparedStatement.setString(3, family_name);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, picture);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            if (rs.getInt(1) == 1) return true;
        }
        return false;
    }

    public ArrayList<User> GetUserList() throws SQLException {
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
    ) throws SQLException{

        // update bảng passwords trước
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "UPDATE passwords SET password = ? WHERE user_id = ?;"
                );
        preparedStatement.setString(1, new_password);
        preparedStatement.setInt(2, user_id);

        if(preparedStatement.executeUpdate() == 0) {
            ServerLog.getInstance().writeLog("Error: Fail to update password table");
            return;
        }

        // sau khi update trên passwords
        // tiến hành update trên user
        preparedStatement.clearBatch();
        preparedStatement = connection.prepareStatement(
                "UPDATE user "
                + "SET first_name = ?, last_name = ?, email = ?, status = ? "
                + "WHERE id = ?;"
        );
        preparedStatement.setString(1, new_firstName);
        preparedStatement.setString(2, new_lastName);
        preparedStatement.setString(3, new_email);
        preparedStatement.setString(4, new_status);
        preparedStatement.setInt(5, user_id);

        if (preparedStatement.executeUpdate() == 0) {
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

    public boolean addNewBook(Book book) {
        String bookID = book.getId();
        String title = truncateString(book.getTitle(), 150);
        ArrayList<String> authors = book.getAuthors().isEmpty() ?
                new ArrayList<>(List.of("Unknown")) : book.getAuthors();
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
            // Kiểm tra sách đã có trong database chưa
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
                    ServerLog.getInstance().writeLog("Failed to insert book into books table.");
                    return false;
                }
            }

            // Thêm tác giả và liên kết sách-tác giả
            for (String author : authors) {
                author = truncateString(author, 100);
                int authorID;

                // Kiểm tra hoặc thêm tác giả
                try (PreparedStatement checkAuthorStmt = connection.prepareStatement(
                        "SELECT author_id FROM authors WHERE name = ?")) {
                    checkAuthorStmt.setString(1, author);
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

                // Thêm liên kết vào bảng `book_authors`
                try (PreparedStatement insertBookAuthorStmt = connection.prepareStatement(
                        "INSERT INTO book_authors(author_id, book_id) VALUES(?, ?)")) {
                    insertBookAuthorStmt.setInt(1, authorID);
                    insertBookAuthorStmt.setString(2, bookID);
                    insertBookAuthorStmt.executeUpdate();
                }
            }

            ServerLog.getInstance().writeLog("Successfully added book and authors to the database.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            ServerLog.getInstance().writeLog("Database error: " + e.getMessage());
            return false;
        }
    }

    private static String truncateString(String str, int maxLength) {
        return (str.length() > maxLength) ? str.substring(0, maxLength) : str;
    }

    public static ArrayList<Book> GetAllDocument() {
        ArrayList<Book> returnList = new ArrayList<>();

        return returnList;
    }
}
