package src;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ImagePacman extends Image implements KeyListener {

    public ImagePacman(WindowGame windowPacmanGame) {
        super(windowPacmanGame);
        imageStart = "images/pacman/left.png";
        icon = new ImageIcon(imageStart);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> leftImgOperations();
            case KeyEvent.VK_RIGHT -> rightImgOperations();
            case KeyEvent.VK_UP -> topImgOperations();
            case KeyEvent.VK_DOWN -> bottomImgOperations();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    protected ImageIcon changeImgByDirection(String direction) throws ErrorDirectionTypeException {
        return switch (direction) {
            case "right" -> new ImageIcon("images/pacman/right.png");
            case "left" -> new ImageIcon("images/pacman/left.png");
            case "down" -> new ImageIcon("images/pacman/down.png");
            case "up" -> new ImageIcon("images/pacman/up.png");
            default -> throw new ErrorDirectionTypeException();
        };
    }

    @Override
    int imgStartPosRow() {
        return windowPacmanGame.getTable().getRowCount() / 2;
    }

    @Override
    int imgStartPosCol() {
        return windowPacmanGame.getTable().getColumnCount() / 2;
    }

}
