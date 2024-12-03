package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.libraryclient.Book.Book;
import project.libraryclient.Client.Client;
import project.libraryclient.ConfirmDialog.ConfirmDialog;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.MySql;
import project.libraryclient.Models.GenerateJson;

import java.io.IOException;

public class BookPreviewController {
    @FXML
    public AnchorPane MainPane;

    @FXML
    public Button ReturnButton, LikeButton, BorrowButton;

    @FXML
    public ImageView BookCover;

    @FXML
    public Label DisplayTitle, DisplayRating, DisplayBorrowTime,
        DisplayAuthors, DisplayCategory, DisplayDescription,
        DisplayISBN10, DisplayISBN13;

//    @FXML
//    public Button ReadBookButton;

    @FXML
    public ImageView Rate1Star, Rate2Star, Rate3Star, Rate4Star, Rate5Star;
    public int rate = 0;

    @FXML
    public Button RateButton;

    private Book SelectedBook;

    public void setInfor(Book book) {
        BookCover.setImage(book.getImagePreview());
        DisplayTitle.setText(book.getTitle());
        DisplayISBN10.setText(book.getISBN_10());
        DisplayISBN13.setText(book.getISBN_13());
        DisplayRating.setText(
                MySql.getInstance().QueryGetAvgRating(book.getId())
        );
        DisplayBorrowTime.setText(
                MySql.getInstance().QueryGetBorrowTime(book.getId())
        );
        DisplayAuthors.setText(String.join(", ", book.getAuthors()));
        DisplayCategory.setText(String.join(", ", book.getCategories()));
        DisplayDescription.setText(book.getDescription());
        SelectedBook = book;
    }

    public void ExitClicked() {
        MainPane.getParent().setVisible(false);
        MainPane.getParent().setDisable(true);
    }

    public void Rate1Clicked() {
        Rate1Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate2Star.setImage(DATA.STAR_BLANK_ICON);
        Rate3Star.setImage(DATA.STAR_BLANK_ICON);
        Rate4Star.setImage(DATA.STAR_BLANK_ICON);
        Rate5Star.setImage(DATA.STAR_BLANK_ICON);
        rate = 1;
    }

    public void Rate2Clicked() {
        Rate1Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate2Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate3Star.setImage(DATA.STAR_BLANK_ICON);
        Rate4Star.setImage(DATA.STAR_BLANK_ICON);
        Rate5Star.setImage(DATA.STAR_BLANK_ICON);
        rate = 2;
    }

    public void Rate3Clicked() {
        Rate1Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate2Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate3Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate4Star.setImage(DATA.STAR_BLANK_ICON);
        Rate5Star.setImage(DATA.STAR_BLANK_ICON);
        rate = 3;
    }

    public void Rate4Clicked() {
        Rate1Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate2Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate3Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate4Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate5Star.setImage(DATA.STAR_BLANK_ICON);
        rate = 4;
    }

    public void Rate5Clicked() {
        Rate1Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate2Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate3Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate4Star.setImage(DATA.STAR_YELLOW_ICON);
        Rate5Star.setImage(DATA.STAR_YELLOW_ICON);
        rate = 5;
    }

    public void RateButtonClicked() throws IOException {
        boolean confirmed = ConfirmDialog.show(
                "Confirm action",
                "Rate this book!"
        );
        if (!confirmed) return;

        if (rate == 0) {
            boolean nonvalue = ConfirmDialog.show(
                    "You haven't choose rate",
                    "Please choose point to rate!"
            );
            if (nonvalue) return;
        }

        Client.getInstance().SendMessage(
                GenerateJson.CreateRatingRequest(
                        Client.getInstance().getUserId(), SelectedBook.getId(), rate));
    }

//    public void ReadButtonClicked() {
//        boolean confirmed = ConfirmDialog.show(
//                "Confirm action",
//                "You will read this book in another window!"
//        );
//        if (!confirmed) return;
//        Stage webViewStage = new Stage();
//        webViewStage.setTitle("WebView Window");
//        WebView webView = new WebView();
//        webView.getEngine().executeScript("document.body.style.zoom = '80%'");
//        webView.getEngine().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
//        webView.getEngine().load(SelectedBook.getWebReaderLink());
//        StackPane webViewRoot = new StackPane(webView);
//        Scene webViewScene = new Scene(webViewRoot, 800, 600);
//        webViewStage.setResizable(true);
//        webViewStage.setScene(webViewScene);
//        webViewStage.show();
//    }
}
