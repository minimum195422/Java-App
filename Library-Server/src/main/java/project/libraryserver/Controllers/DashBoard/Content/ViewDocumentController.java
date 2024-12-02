package project.libraryserver.Controllers.DashBoard.Content;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import project.libraryserver.Book.Book;
import project.libraryserver.ConfirmDialog.ConfirmDialog;
import project.libraryserver.Database.MySql;
import project.libraryserver.Server.ServerLog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ViewDocumentController {
    @FXML
    public AnchorPane MainPane;

    @FXML
    public Button ReturnButton, SaveButton, DeleteButton;

    @FXML
    public Text WarningText;

    @FXML
    public ImageView SelectedCover;


    @FXML
    TextArea SelectedBookId,SelectedBookTitle, SelectedBookAuthors,
            SelectedBookPublisher, SelectedBookPublishedDate,
            SelectedBookDescription, SelectedBookCategories,
            SelectedBookIsbn13, SelectedBookIsbn10,
            SelectedBookReadLink;

    @FXML
    public WebView PreviewBook;

    private Book SelectedBook;

    public void setInfor(Book book) {
        SelectedBookId.setText(book.getId());
        SelectedBookTitle.setText(book.getTitle());
        SelectedBookAuthors.setText(String.join(", ", book.getAuthors()));
        SelectedBookPublisher.setText(book.getPublisher());
        SelectedBookPublishedDate.setText(book.getPublishedDate());
        SelectedBookDescription.setText(book.getDescription());
        SelectedBookCategories.setText(String.join(", ", book.getCategories()));
        SelectedBookIsbn13.setText(book.getISBN_13());
        SelectedBookIsbn10.setText(book.getISBN_10());
        SelectedCover.setImage(book.getImagePreview());
        SelectedBookReadLink.setText(book.getWebReaderLink());


        PreviewBook.getEngine().executeScript("document.body.style.zoom = '80%'");
        PreviewBook.getEngine().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        PreviewBook.getEngine().setJavaScriptEnabled(true);
        PreviewBook.getEngine().load(book.getWebReaderLink());
        SelectedBook = book;
    }

    public void SaveButtonClicked() {
        boolean confirmed = ConfirmDialog.show(
                "Confirm action",
                "Change book information!"
        );
        if (!confirmed) return;

        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() throws SQLException {
                SelectedBook = new Book(
                    SelectedBookId.getText(),
                    SelectedBookTitle.getText(),
                    new ArrayList<>(List.of(SelectedBookAuthors.getText().split(","))),
                    SelectedBookPublisher.getText(),
                    SelectedBookPublishedDate.getText(),
                    SelectedBookDescription.getText(),
                    new ArrayList<>(List.of(SelectedBookCategories.getText().split(","))),
                    SelectedBookIsbn13.getText(),
                    SelectedBookIsbn10.getText(),
                    SelectedCover.getImage(),
                    SelectedBookReadLink.getText()
                );
                return MySql.getInstance().UpdateBookInformation(SelectedBook);
            }
        };

        task.setOnSucceeded(_ -> {
            boolean result = task.getValue();
            if (result) {
                WarningText.setText("Successfully change book information.");
                ServerLog.getInstance().writeLog("Action: Change information of book " + SelectedBook.getId());
                setInfor(SelectedBook);
            } else {
                WarningText.setText("Failed to change information, please try again!");
            }
        });

        task.setOnFailed(_ -> WarningText.setText("An error occurred while changing the book."));

        new Thread(task).start();

        WarningText.setText("Update book...");
    }

    public void DeleteButtonClicked() {
        boolean confirmed = ConfirmDialog.show(
                "Confirm action",
                "Delete this book!"
        );
        if (!confirmed) return;

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws SQLException {
                MySql.getInstance().DeleteBook(SelectedBook.getId());
                return null;
            }
        };

        task.setOnSucceeded(_ -> {
            ServerLog.getInstance().writeLog("Action: Change information of book " + SelectedBook.getId());
            boolean exitConfirm = ConfirmDialog.show(
                    "Successfully deleted this book",
                    "Exit this page!"
            );
            if (exitConfirm) {
                Platform.runLater(()-> {
                    MainPane.getParent().setVisible(false);
                    MainPane.getParent().setDisable(true);
                });
            }
        });

        task.setOnFailed(_ -> WarningText.setText("An error occurred while deleting the book."));

        new Thread(task).start();

        WarningText.setText("Delete book...");
    }

    public void ReturnClicked() {
        MainPane.getParent().setVisible(false);
        MainPane.getParent().setDisable(true);
    }
}
