package project.libraryserver.Controllers.DashBoard.Content;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import project.libraryserver.API.GoogleBookAPI.BookAPI;
import project.libraryserver.Book.Book;
import project.libraryserver.ConfirmDialog.ConfirmDialog;
import project.libraryserver.Consts.SearchType;
import project.libraryserver.Database.MySql;
import project.libraryserver.Server.ServerLog;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewBookController implements Initializable {

    @FXML
    public VBox DisplayListBook;

    @FXML
    public Button SearchButton;

    @FXML
    public TextField SearchQuery;

    @FXML
    public ToggleButton SearchByAuthorButton;

    @FXML
    public ToggleButton SearchByTitleButton;

    @FXML
    public ToggleButton SearchByPublisherButton;

    @FXML
    public ToggleButton SearchBySubjectButton;

    @FXML
    public ToggleButton SearchByIsbnButton;


    @FXML
    public Button AddNewBookButton;

    @FXML
    public Label SelectedBookId, SelectedBookTitle, SelectedBookAuthors,
            SelectedBookPublisher, SelectedBookPublishedDate, SelectedBookDescription,
            SelectedBookCategories, SelectedBookIsbn13, SelectedBookIsbn10,
            SelectedBookReadLink, WarningText;

    public Book SelectedBook;

    @FXML
    public ImageView SelectedBookCover;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayListBook.setSpacing(10);
        WarningText.setStyle("-fx-text-fill: red;");

        // Display text when mouse on choosing
        Tooltip tooltip = new Tooltip("Open in Browser");
        tooltip.setShowDelay(Duration.millis(100));
        Tooltip.install(SelectedBookReadLink, tooltip);
    }

    public void SearchAction() throws URISyntaxException, IOException {
        ArrayList<SearchType> types = new ArrayList<>();
        if (SearchByAuthorButton.isSelected()) types.add(SearchType.INAUTHOR);
        if (SearchByTitleButton.isSelected()) types.add(SearchType.INTITLE);
        if (SearchByPublisherButton.isSelected()) types.add(SearchType.INPUBLISHER);
        if (SearchBySubjectButton.isSelected()) types.add(SearchType.INSUBJECT);
        if (SearchByIsbnButton.isSelected()) types.add(SearchType.INISBN);
        if (SearchQuery.getText().isEmpty()) return;
        ArrayList<Book> list = BookAPI.SearchBook(SearchQuery.getText(), types);

        UpdateBookList(list);
    }

    public void UpdateBookList(ArrayList<Book> list) {
        if (!DisplayListBook.getChildren().isEmpty()) DisplayListBook.getChildren().clear();

        for (Book book : list) {
            DisplayListBook.getChildren().add(book.GetDisplayCardForGoogleSearch());
//            MySql.getInstance().AddNewBook(book);
            book.GetDisplayCardForGoogleSearch().setOnMouseClicked(
                    _ -> {
                        SelectedBookId.setText(book.getId());
                        SelectedBookTitle.setText(book.getTitle());
                        SelectedBookAuthors.setText(String.join(", ", book.getAuthors()));
                        SelectedBookPublisher.setText(book.getPublisher());
                        SelectedBookPublishedDate.setText(book.getPublishedDate());
                        SelectedBookDescription.setText(book.getDescription());
                        SelectedBookCategories.setText(String.join(", ", book.getCategories()));
                        SelectedBookIsbn13.setText(book.getISBN_13());
                        SelectedBookIsbn10.setText(book.getISBN_10());
                        SelectedBookReadLink.setText(book.getWebReaderLink());
                        SelectedBookCover.setImage(book.getImagePreview());
                        SelectedBook = book;

                    });
        }
    }

    public void ReaderLinkClicked() {
        if (SelectedBookReadLink.getText().isEmpty()) return;
        try {
            Desktop.getDesktop().browse(new URI(SelectedBookReadLink.getText()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddNewBookButtonClicked() {

        if (SelectedBook == null) {
            WarningText.setText("No data selected");
            WarningText.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean confirmed = ConfirmDialog.show(
                "Confirm action",
                "Add new book to database!"
        );
        if (!confirmed) return;

        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() {
                return MySql.getInstance().AddNewBook(SelectedBook);
            }
        };

        task.setOnSucceeded(_ -> {
            boolean result = task.getValue();
            if (result) {
                WarningText.setText("Successfully added new book to database");
                WarningText.setStyle("-fx-text-fill: green;");
                ServerLog.getInstance().writeLog("Action: Add new book " + SelectedBook.getId() + " to database.");
            } else {
                WarningText.setText("Failed to add new book to database");
                WarningText.setStyle("-fx-text-fill: red;");
            }
        });

        task.setOnFailed(_ -> {
            WarningText.setText("An error occurred while adding the book.");
            WarningText.setStyle("-fx-text-fill: red;");
        });

        new Thread(task).start();

        WarningText.setText("Adding new book to database...");
        WarningText.setStyle("-fx-text-fill: red;");
    }

}
