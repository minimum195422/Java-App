package project.libraryserver.Controllers.DashBoard.Content;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import project.libraryserver.Book.Book;
import project.libraryserver.Book.BookSort;
import project.libraryserver.ConfirmDialog.ConfirmDialog;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Consts.SearchType;
import project.libraryserver.Database.MySql;
import project.libraryserver.Server.ServerLog;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ManageDocumentController implements Initializable{
    @FXML
    public VBox DisplayBookList;

    @FXML
    public BorderPane HiddenPane;

    @FXML
    public TextField SearchBox;

    @FXML
    public ToggleButton SearchById, SearchByTitle, SearchByAuthor;
    public ToggleGroup SearchOption = new ToggleGroup();
    public ToggleGroup SortOption = new ToggleGroup();

    @FXML
    public Button SearchButton;

    @FXML
    public ToggleButton SortById, SortByTitle, SortByRating, SortByBorrowedTime, SortDirection;

    ArrayList<Book> BookList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HiddenPane.setVisible(false);
        HiddenPane.setDisable(true);
        HiddenPane.visibleProperty().addListener((_, oldValue, _) -> {
            if (oldValue) {
                LoadBookList();
            }
        });

        SearchById.setToggleGroup(SearchOption);
        SearchByTitle.setToggleGroup(SearchOption);
        SearchByAuthor.setToggleGroup(SearchOption);

        SearchById.setSelected(true);

        SortById.setToggleGroup(SortOption);
        SortByTitle.setToggleGroup(SortOption);
        SortByRating.setToggleGroup(SortOption);
        SortByBorrowedTime.setToggleGroup(SortOption);

        SortById.setSelected(true);
        SortDirection.setSelected(true);
        // Set button group always have one in used
        SearchOption.selectedToggleProperty().addListener(
                (_, oldToggle, newToggle) -> {
                    if (newToggle == null) {
                        SearchOption.selectToggle(oldToggle);
                    }
                });

        SortOption.selectedToggleProperty().addListener(
                (_, oldToggle, newToggle) -> {
                    if (newToggle == null) {
                        SortOption.selectToggle(oldToggle);
                    }
                });
        LoadBookList();
    }

    private void LoadBookList() {
        BookList = MySql.getInstance().GetAllDocumentForManage();
        if (BookList.isEmpty()) {
            System.out.println("Failed to load book list");
            return;
        }

        ReloadDisplayBookList();
    }

    private void ReloadDisplayBookList() {
        if (!DisplayBookList.getChildren().isEmpty()) DisplayBookList.getChildren().clear();

        for (Book book : BookList) {
            DisplayBookList.getChildren().add(
                    book.GetDisplayCardForManage()
            );

            book.GetBook_1020_50_Controller().DeleteButton.setOnMouseClicked(
                    _ -> {
                        boolean confirmed = ConfirmDialog.show(
                                "Confirm action",
                                "Delete this book!"
                        );
                        if (!confirmed) return;

                        Task<Void> task = new Task<>() {
                            @Override
                            protected Void call() throws SQLException {
                                MySql.getInstance().DeleteBook(book.getId());
                                return null;
                            }
                        };

                        task.setOnSucceeded(_ -> {
                            ServerLog.getInstance().writeLog("Action: Delete book with id = [" + book.getId() + "]");
                            LoadBookList();
                        });

                        task.setOnFailed(_ -> ServerLog.getInstance().writeLog("An error occurred while deleting the book."));

                        new Thread(task).start();
                    }
            );

            book.GetBook_1020_50_Controller().ViewButton.setOnMouseClicked(
                    _ -> {
                        Book fullBook = MySql.getInstance().GetBookById(book.getId());
                        if (fullBook == null) return;
                        HiddenPane.setVisible(true);
                        HiddenPane.setDisable(false);
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.VIEW_DOCUMENT_LINK));
                            AnchorPane pane = loader.load();
                            ViewDocumentController controller = loader.getController();
                            controller.setInfo(fullBook);
                            HiddenPane.setCenter(pane);
                        } catch (IOException e) {
                            e.printStackTrace(System.out);
                            ServerLog.getInstance().writeLog("Error: Fail to load view document page!");
                        }
                    }
            );

        }

    }

    public void SearchOnAction() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                SearchType searchOption = null;
                if (SearchById.isSelected()) searchOption = SearchType.INID;
                if (SearchByAuthor.isSelected()) searchOption = SearchType.INAUTHOR;
                if (SearchByTitle.isSelected()) searchOption = SearchType.INTITLE;

                BookList = MySql.getInstance().GetSearchBookList(SearchBox.getText(), searchOption);

                Platform.runLater(() -> {
                    ReloadDisplayBookList();
                });

                return null;
            }
        };

        new Thread(task).start();
    }

    public void SortBookList() {
        if(!SortDirection.isSelected()) {
            SortDirection.setSelected(true);
            if (SortDirection.getText().equals("ASC")) SortDirection.setText("DESC");
            else SortDirection.setText("ASC");
        }
        if (SortDirection.getText().equals("ASC")){
            if (SortById.isSelected()) BookSort.SortByIdAsc(BookList);
            if (SortByTitle.isSelected()) BookSort.SortByTitleAsc(BookList);
            if (SortByRating.isSelected()) BookSort.SortByRatingAsc(BookList);
            if (SortByBorrowedTime.isSelected()) BookSort.SortByBorrowedTimeAsc(BookList);
        } else {
            if (SortById.isSelected()) BookSort.SortByIdDesc(BookList);
            if (SortByTitle.isSelected()) BookSort.SortByTitleDesc(BookList);
            if (SortByRating.isSelected()) BookSort.SortByRatingDesc(BookList);
            if (SortByBorrowedTime.isSelected()) BookSort.SortByBorrowedTimeDesc(BookList);
        }
        ReloadDisplayBookList();
    }
}
