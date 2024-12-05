package project.libraryserver.Controllers.Card;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.JSONObject;
import project.libraryserver.ConfirmDialog.ConfirmDialog;
import project.libraryserver.Consts.JsonType;
import project.libraryserver.Database.MySql;
import project.libraryserver.Models.GenerateJson;
import project.libraryserver.Models.JsonFileHandler;


public class BorrowBookCardController {

    @FXML
    public Label DisplayUserId, DisplayBookId;

    @FXML
    public Button AcceptedButton, DeclinedButton, RecallButton;

    private JSONObject localJson;

    public void setInfo(JSONObject json) {
        this.localJson = json;

        DisplayUserId.setText("" + localJson.getInt("user_id"));
        DisplayBookId.setText(localJson.getString("book_id"));

        JsonType status = JsonType.valueOf(localJson.getString("status"));
        switch (status) {
            case PENDING -> RecallButton.setDisable(true);
            case BORROW_ACCEPTED -> {
                RecallButton.setDisable(false);

                AcceptedButton.getStyleClass().remove("green-button");
                AcceptedButton.getStyleClass().add("gray-button");
                AcceptedButton.setDisable(true);

                DeclinedButton.getStyleClass().remove("red-button");
                DeclinedButton.getStyleClass().add("gray-button");
                DeclinedButton.setDisable(true);
            }
        }
    }

    public void AcceptedButtonClicked() {
        boolean confirm = ConfirmDialog.show(
                "Confirm action",
                "Accept user borrow request");
        if (!confirm) return;

        JsonFileHandler.getInstance().removeJsonObject(localJson);
        JsonFileHandler.getInstance().addJsonObject(GenerateJson.ResponseAcceptBorrowBook(
                localJson.getInt("user_id"),
                localJson.getString("book_id"),
                MySql.getInstance().QueryGetReadLinkByBookId(localJson.getString("book_id"))));
        MySql.getInstance().IncreaseBorrowTimes(DisplayBookId.getText());
    }

    public void DeclinedButtonClicked() {
        boolean confirm = ConfirmDialog.show(
                "Confirm action",
                "Decline user borrow request");
        if (!confirm) return;
        JsonFileHandler.getInstance().removeJsonObject(localJson);
        JsonFileHandler.getInstance().addJsonObject(
                GenerateJson.ResponseDeclineBorrowBook(
                        localJson.getInt("user_id"),
                        localJson.getString("book_id")));
    }

    public void RecallButtonClicked() {
        boolean confirm = ConfirmDialog.show(
                "Confirm action",
                "Recall this book from client");
        if (!confirm) return;
        JsonFileHandler.getInstance().removeJsonObject(localJson);
        JsonFileHandler.getInstance().addJsonObject(
                GenerateJson.ResponseBookRecall(
                        localJson.getInt("user_id"),
                        localJson.getString("book_id")));
    }
}
