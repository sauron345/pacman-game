package src;
import javax.swing.*;

abstract public class Image extends ImageIcon {
    protected final WindowGame windowPacmanGame;
    protected int currImgPosRow, currImgPosCol;
    protected String imageStart;
    protected ImageIcon icon;

    public Image(WindowGame windowPacmanGame) {
        this.windowPacmanGame = windowPacmanGame;
        currImgPosRow = imgStartPosRow();
        currImgPosCol = imgStartPosCol();
    }

    protected void leftImgOperations() {
        icon = changeImgByDirection("left");
        if (currImgPosCol > 0 && !isWallCollLeftNext())
            moveImgLeft();
        else
            changeImgOnly();
    }

    protected void rightImgOperations() {
        icon = changeImgByDirection("right");
        if (currImgPosCol < getTableRightBorder() && !isWallCollRightNext())
            moveImgRight();
        else
            changeImgOnly();
    }

    protected void topImgOperations() {
        icon = changeImgByDirection("up");
        if (currImgPosRow > 0 && !isWallRowTopNext())
            moveImgUp();
        else
            changeImgOnly();
    }

    protected void bottomImgOperations() {
        icon = changeImgByDirection("down");
        if (currImgPosRow < getTableBottomBorder() && !isWallRowBottomNext())
            moveImgDown();
        else
            changeImgOnly();
    }

    abstract protected ImageIcon changeImgByDirection(String direction) throws ErrorDirectionTypeException;

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

    abstract int imgStartPosRow();

    abstract int imgStartPosCol();

    protected ImageIcon getIcon() {
        return icon;
    }

    public int getCurrImgPosRow() {
        return currImgPosRow;
    }

    public int getCurrImgPosCol() {
        return currImgPosCol;
    }

}
