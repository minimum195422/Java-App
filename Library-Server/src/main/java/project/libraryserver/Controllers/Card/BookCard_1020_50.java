package project.libraryserver.Controllers.Card;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BookCard_1020_50 {
    public Label DisplayId;
    public Label DisplayTitle;
    public Label DisplayAuthors;
    public Label DisplayRate;
    public Label DisplayBorrowedTime;
    public Button DeleteButton;
    public Button ViewButton;

    public void setInfor(
            String id,
            String title,
            String author,
            String rate,
            String borrowTime
    ) {
        DisplayId.setText(id);
        DisplayTitle.setText(title);
        DisplayAuthors.setText(author);
        DisplayRate.setText(rate);
        DisplayBorrowedTime.setText(borrowTime);
    }
}
