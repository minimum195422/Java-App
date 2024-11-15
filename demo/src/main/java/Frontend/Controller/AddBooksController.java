package Frontend.Controller;

import Backend.QueryHandler;
import Frontend.Library.SceneHandler;
import Frontend.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Objects;

public class AddBooksController {
    @FXML
    private Text errorText;
    @FXML
    private ImageView imagePreview;
    @FXML
    private Hyperlink returnLink;
    @FXML
    private TextField imageURL;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField publishedDate;
    @FXML
    private TextField ISBN_13;
    @FXML
    private TextField price;
    @FXML
    private Button addButton;

    private boolean isEditing = false;
    private boolean isAddFocusedProperty = false;
    private String oldURL = "";

    private void resetAll() {
        errorText.setVisible(false);
        errorText.setStyle("-fx-fill: red;");
        isEditing = false;
        oldURL = "";
        try {
            imagePreview.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Background/no_image.png"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        imageURL.clear();
        title.clear();
        author.clear();
        publishedDate.clear();
        ISBN_13.clear();
        price.clear();
    }

    @FXML
    private void redirectToDashboard(ActionEvent actionEvent) {
        SceneHandler.getInstance(Main.class, null).setScene("DashboardPage");
        resetAll();
    }

    @FXML
    private void handleImageURL(MouseEvent event) {
        if (!isAddFocusedProperty) {
            isAddFocusedProperty = true;
            imageURL.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
                if (newFocus) {  // TextField gained focus
                    isEditing = true;
                    System.out.println("Editing started.");
                } else {  // TextField lost focus
                    isEditing = false;
                    System.out.println("Editing stopped.");
                    String s = imageURL.getText();
                    if (!s.equals(oldURL)) {
                        try {
                            imagePreview.setImage(new Image(s));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        oldURL = s;
                    }
                }
            });
        }
    }

    @FXML
    private void addBook(ActionEvent actionEvent) throws SQLException {
        String imageURLText = imageURL.getText();
        String titleText = title.getText();
        String authorText = author.getText();
        String publishedDateText = publishedDate.getText();
        String ISBN_13Text = ISBN_13.getText();
        int priceValue;
        if (price.getText().isEmpty()) {
            priceValue = 0;
        } else {
            priceValue = Integer.parseInt(price.getText());
        }
        if (imageURLText.isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("Image URL is empty");
            return;
        }
        if (titleText.isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("Title is empty");
            return;
        }
        if (ISBN_13Text.isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("ISBN_13 is empty");
            return;
        }
        if (authorText.isEmpty()) {
            authorText = "Unknown";
        }
        if (publishedDateText.isEmpty()) {
            publishedDateText = "Unknown";
        }
        System.out.println(imageURLText);
        System.out.println(titleText);
        System.out.println(authorText);
        System.out.println(publishedDateText);
        System.out.println(ISBN_13Text);
        System.out.println(priceValue);
        QueryHandler.addNewBook(imageURLText, titleText, authorText, publishedDateText, ISBN_13Text, priceValue);
        resetAll();
        errorText.setVisible(true);
        errorText.setText("Added successfully!");
        errorText.setStyle("-fx-fill: green;");
    }
}
