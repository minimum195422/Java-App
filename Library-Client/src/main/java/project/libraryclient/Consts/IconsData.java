package project.libraryclient.Consts;

import javafx.scene.image.Image;

import java.util.Objects;

public class IconsData {
    public static final Image HOME_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/homewhite.png");
    public static final Image HOME_GREEN_ICON = loadImage("/project/libraryclient/Images/Icons/homegreen.png");
    public static final Image HEART_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/heartwhite.png");
    public static final Image HEART_GREEN_ICON = loadImage("/project/libraryclient/Images/Icons/heartgreen.png");

    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(IconsData.class.getResourceAsStream(path)));
    }
}
