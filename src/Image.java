package src;
import javax.swing.*;
import java.awt.*;

abstract public class Image extends JLabel {
    protected final WindowGame windowGame;
    protected int currImgPosRow, currImgPosCol;
    protected String imgStart;
    protected ImageIcon currImgDirection;
    protected ImageIcon currImgAnimation;
    protected int currImgNum = 0;
    protected String currDirection;
    protected ImageIcon leftImg, rightImg, upImg, downImg;
    protected ImageIcon leftAnim, rightAnim, upAnim, downAnim;


    public Image(WindowGame windowPacmanGame) {
        this.windowGame = windowPacmanGame;
        imgStartPos();
    }

    public void imgStartPos() {
        currImgPosRow = imgStartPosRow();
        currImgPosCol = imgStartPosCol();
    }

    public Image(WindowGame windowPacmanGame, int currImgPosRow, int currImgPosCol) {
        this.windowGame = windowPacmanGame;
        this.currImgPosRow = currImgPosRow;
        this.currImgPosCol = currImgPosCol;
    }

    protected void changeImgForAnimation(JLabel lbl) {
        if (getCurrImgNum() == 0) {
            lbl.setIcon(getCurrImgDirection());
            currImgNum = 1;
        } else {
            lbl.setIcon(getImgAnimation());
            currImgNum = 0;
        }
    }

    protected ImageIcon changeImgByDirection(String direction) throws ErrorDirectionTypeException {
        return switch (direction) {
            case "right" -> rightImg;
            case "left" -> leftImg;
            case "down" -> downImg;
            case "up" -> upImg;
            default -> throw new ErrorDirectionTypeException();
        };
    }

    protected ImageIcon changeImgAnimationByDir(String direction) throws ErrorDirectionTypeException {
        return switch (direction) {
            case "right" -> rightAnim;
            case "left" -> leftAnim;
            case "down" -> downAnim;
            case "up" -> upAnim;
            default -> throw new ErrorDirectionTypeException();
        };
    }

    abstract protected void changePos(JLabel lbl);

    abstract protected void leftImgOperations();

    abstract protected void rightImgOperations();

    abstract protected void topImgOperations();

    abstract protected void bottomImgOperations();

    abstract protected void imgsDirections();

    abstract protected void imgsAnimations();

    void moveImgRight() throws ErrorDirectionTypeException {
        currImgPosCol++;
        windowGame.displayImgInDifferentPos(this);
    }

    protected void moveImgLeft() throws ErrorDirectionTypeException {
        currImgPosCol--;
        windowGame.displayImgInDifferentPos(this);
    }

    protected void moveImgUp() throws ErrorDirectionTypeException {
        currImgPosRow--;
        windowGame.displayImgInDifferentPos(this);
    }

    protected void moveImgDown() throws ErrorDirectionTypeException {
        currImgPosRow++;
        windowGame.displayImgInDifferentPos(this);
    }

    protected int getTableRightBorder() {
        return windowGame.getTable().getColumnCount()-1;
    }

    protected int getTableBottomBorder() {
        return windowGame.getTable().getRowCount()-1;
    }

    protected boolean isWallRowBottomNext() {
        return windowGame.isWallExistsIn(currImgPosRow + 1, currImgPosCol);
    }

    protected boolean isWallRowTopNext() {
        return windowGame.isWallExistsIn(currImgPosRow - 1, currImgPosCol);
    }

    protected boolean isWallCollLeftNext() {
        return windowGame.isWallExistsIn(currImgPosRow, currImgPosCol - 1);
    }

    protected boolean isWallCollRightNext() {
        return windowGame.isWallExistsIn(currImgPosRow, currImgPosCol + 1);
    }

    abstract int imgStartPosRow();

    abstract int imgStartPosCol();

    protected ImageIcon getCurrImgDirection() {
        return currImgDirection;
    }

    public int getCurrPosRow() {
        return currImgPosRow;
    }

    public int getCurrPosCol() {
        return currImgPosCol;
    }

    public ImageIcon getImgAnimation() {
        return currImgAnimation;
    }

    public int getCurrImgNum() {
        return currImgNum;
    }


}
