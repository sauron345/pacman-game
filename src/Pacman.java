import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman extends JLabel implements KeyListener {
    private final WindowPacmanGame windowPacmanGame;
    private int currImgPosRow, currImgPosCol;
    private ImageIcon icon = new ImageIcon("images/pacman-right.png");

    public Pacman(WindowPacmanGame windowPacmanGame) {
        this.windowPacmanGame = windowPacmanGame;
        currImgPosRow = imgStartPosRow();
        currImgPosCol = imgStartPosCol();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_LEFT -> {
                leftImgOperations();
            }
            case KeyEvent.VK_RIGHT -> {
                rightImgOperations();
            }
            case KeyEvent.VK_UP -> {
                topImgOperations();
            }
            case KeyEvent.VK_DOWN -> {
                bottomImgOperations();
            }
        }
    }

    private void leftImgOperations() {
        icon = changeImgByDirection("left");
        if (currImgPosCol > 0 && !isWallCollLeftNext())
            moveImgLeft();
        else
            changeImgOnly();
    }

    private void rightImgOperations() {
        icon = changeImgByDirection("right");
        if (currImgPosCol < getTableRightBorder() && !isWallCollRightNext())
            moveImgRight();
        else
            changeImgOnly();
    }

    private void topImgOperations() {
        icon = changeImgByDirection("up");
        if (currImgPosRow > 0 && !isWallRowTopNext())
            moveImgUp();
        else
            changeImgOnly();
    }

    private void bottomImgOperations() {
        icon = changeImgByDirection("down");
        if (currImgPosRow < getTableBottomBorder() && !isWallRowBottomNext())
            moveImgDown();
        else
            changeImgOnly();
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    private ImageIcon changeImgByDirection(String direction) throws ErrorDirectionTypeException {
        return switch (direction) {
            case "right" -> new ImageIcon("images/pacman-right.png");
            case "left" -> new ImageIcon("images/pacman-left.png");
            case "down" -> new ImageIcon("images/pacman-down.png");
            case "up" -> new ImageIcon("images/pacman-up.png");
            default -> throw new ErrorDirectionTypeException();
        };
    }

    private void moveImgRight() throws ErrorDirectionTypeException {
        currImgPosCol++;
        windowPacmanGame.displayImgInDifferentPos();
    }

    private void moveImgLeft() throws ErrorDirectionTypeException {
        currImgPosCol--;
        windowPacmanGame.displayImgInDifferentPos();
    }

    private void moveImgUp() throws ErrorDirectionTypeException {
        currImgPosRow--;
        windowPacmanGame.displayImgInDifferentPos();
    }

    private void moveImgDown() throws ErrorDirectionTypeException {
        currImgPosRow++;
        windowPacmanGame.displayImgInDifferentPos();
    }

    private int getTableRightBorder() {
        return windowPacmanGame.getTable().getColumnCount()-1;
    }

    private int getTableBottomBorder() {
        return windowPacmanGame.getTable().getRowCount()-1;
    }

    private void changeImgOnly() throws ErrorDirectionTypeException {
        windowPacmanGame.displayImgInDifferentPos();
    }

    private boolean isWallRowBottomNext() {
        return windowPacmanGame.getWallsRowsIndexes().contains(currImgPosRow+1) && isWallColSame();
    }

    private boolean isWallRowTopNext() {
        return windowPacmanGame.getWallsRowsIndexes().contains(currImgPosRow-1) && isWallColSame();
    }

    private boolean isWallCollLeftNext() {
        return windowPacmanGame.getWallsColsIndexes().contains(currImgPosCol-1) && isWallRowSame();
    }

    private boolean isWallCollRightNext() {
        return windowPacmanGame.getWallsColsIndexes().contains(currImgPosCol+1) && isWallRowSame();
    }

    private boolean isWallColSame() {
        return windowPacmanGame.getWallsColsIndexes().contains(currImgPosCol);
    }

    private boolean isWallRowSame() {
        return windowPacmanGame.getWallsRowsIndexes().contains(currImgPosRow);
    }

    private int imgStartPosRow() {
        return windowPacmanGame.getTable().getRowCount() / 2;
    }

    private int imgStartPosCol() {
        return windowPacmanGame.getTable().getColumnCount() / 2;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int getCurrImgPosRow() {
        return currImgPosRow;
    }

    public int getCurrImgPosCol() {
        return currImgPosCol;
    }
}
