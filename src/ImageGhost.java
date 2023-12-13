package src;
import javax.swing.*;

public class ImageGhost extends Image {
    private final String type;

    public ImageGhost(WindowGame windowPacmanGame, String type) {
        super(windowPacmanGame);
        this.type = type;
        imageStart = "images/ghosts/" + type + "/left.png";
        icon = new ImageIcon(imageStart);
    }

    @Override
    protected ImageIcon changeImgByDirection(String direction) throws ErrorDirectionTypeException {
        return switch (direction) {
            case "right" -> new ImageIcon("images/ghosts/" + type + "/right.png");
            case "left" -> new ImageIcon("images/ghosts/" + type + "/left.png");
            case "down" -> new ImageIcon("images/ghosts/" + type + "/down.png");
            case "up" -> new ImageIcon("images/ghosts/" + type + "/up.png");
            default -> throw new ErrorDirectionTypeException();
        };
    }

    @Override
    int imgStartPosRow() {
        return 0;
    }

    @Override
    int imgStartPosCol() {
        return 0;
    }


}
