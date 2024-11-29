package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.libraryclient.Class.Book;
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
            while (bookNameList.size() > 10) {
                bookNameList.removeLast();
            }
            updateSearchResult();
        }  catch (SQLException _) {
            System.out.println("Error while initializing home page");
        }
    }

    private void updateSearchResult() {
        for (String name : bookNameList) {
            try {
                ArrayList<Book> bookNameList = MySql.getBasicInfoOfBook(name);
                for (Book book : bookNameList) {
                    if (list.size() >= 20) {
                        break;
                    }
//                    System.out.println(book.getTitle());
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
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error while searching books");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContent();
        int row = (list.size() + 4) / 5;
        for (int i = 0; i < row; ++i) {
            HBox vbox = new HBox();
            vbox.setSpacing(25);
            for (int j = 0; j < 5; ++j) {
                if (i * 5 + j >= list.size()) break;
                vbox.getChildren().add(list.get(i * 5 + j));
            }
            MainVBox.getChildren().add(vbox);
        }
    }
}
