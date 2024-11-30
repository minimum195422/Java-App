package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.libraryclient.Book.Book;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Controllers.Card.Card_235_450_Controller;
import project.libraryclient.Database.MySql;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public VBox MainVBox;
    ArrayList<String> bookNameList = new ArrayList<>();
    List<AnchorPane> list = new ArrayList<>();
    public void setContent() {
        try {
            bookNameList = MySql.getBookBySubstring("");
            for (int i = 1; i < bookNameList.size(); i++) {
                Random rand = new Random();
                int j = rand.nextInt(i);
                String temp = bookNameList.get(i);
                bookNameList.set(i, bookNameList.get(j));
                bookNameList.set(j, temp);
            }
            updateSearchResult();
//            System.out.println(list.size());
        }  catch (SQLException _) {
            System.out.println("Error while initializing home page");
        }
    }

    private void updateSearchResult() {
        for (String name : bookNameList) {
            if (list.size() >= 8) {
                break;
            }
            try {
                ArrayList<Book> bookNameList = MySql.getBasicInfoOfBook(name);
                for (Book book : bookNameList) {
                    if (list.size() >= 8) {
                        break;
                    }
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.CARD_235_450));
                        AnchorPane card = loader.load();
                        Card_235_450_Controller controller = loader.getController();

                        controller.setAuthorName(book.getAuthors());
                        controller.setBookCover(book.getImagePreview());
                        controller.setBookName(book.getTitle());

                        list.add(card);
//                             Add listener to a book
                        card.setOnMouseClicked(event -> {
                            System.out.println(book.getId());
                        });
                    } catch (IOException e) {
                        System.out.println("Error while searching books");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContent();
        int row = (list.size() + 3) / 4;
        for (int i = 0; i < row; ++i) {
            HBox vbox = new HBox();
            vbox.setSpacing(75);
            vbox.setPadding(new Insets(0, 0, 0, 30));
            for (int j = 0; j < 4; ++j) {
                if (i * 4 + j >= list.size()) break;
                vbox.getChildren().add(list.get(i * 4 + j));
            }
            MainVBox.getChildren().add(vbox);
        }
    }
}
