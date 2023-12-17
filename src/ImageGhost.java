package src;
import javax.swing.*;
import java.awt.*;
import static java.lang.Thread.sleep;

public class ImageGhost extends Image implements Runnable {
    private final String type;
    private final String[] availDirect = {"left", "right", "down", "up"};
    private boolean running = false;

    public ImageGhost(WindowGame windowGame, String type, int startPosRow, int startPosCol) {
        super(windowGame, startPosRow, startPosCol);
        this.type = type;
        imageConfig();
        imgsDirections();
        imgsAnimations();
    }

    private void imageConfig() {
        currDirection = changeImgRandDirection();
        imgStart = "images/ghosts/" + type + "/" + currDirection + ".png";
        currImgDirection = new ImageIcon(imgStart);
    }

    @Override
    protected void imgsDirections() {
        leftImg = new ImageIcon("images/ghosts/" + type + "/left.png");
        rightImg = new ImageIcon("images/ghosts/" + type + "/right.png");
        downImg = new ImageIcon("images/ghosts/" + type + "/down.png");
        upImg = new ImageIcon("images/ghosts/" + type + "/up.png");
    }

    @Override
    protected void imgsAnimations() {
        leftAnim = new ImageIcon("images/ghosts/" + type + "/animation/left.png");
        rightAnim = new ImageIcon("images/ghosts/" + type + "/animation/right.png");
        downAnim = new ImageIcon("images/ghosts/" + type + "/animation/down.png");
        upAnim = new ImageIcon("images/ghosts/" + type + "/animation/up.png");
    }

    @Override
    public void run() {
        try {
            sleep(600);
            imgOperations();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void imgOperations() {
        switch(currDirection) {
            case "left" -> leftImgOperations();
            case "right" -> rightImgOperations();
            case "down" -> bottomImgOperations();
            case "up" -> topImgOperations();
            default -> throw new ErrorDirectionTypeException();
        }
    }

    protected String changeImgRandDirection() {
        int directInd = (int) (Math.random() * (3 + 1) + 0);
        return availDirect[directInd];
    }

    protected void rightImgOperations() {
        if (currImgPosCol < getTableRightBorder() && !isWallCollRightNext())
            moveImgRight();
        else
            getNewDirection();
    }

    protected void topImgOperations() {
        if (currImgPosRow > 0 && !isWallRowTopNext())
            moveImgUp();
        else
            getNewDirection();
    }

    protected void bottomImgOperations() {
        if (currImgPosRow < getTableBottomBorder() && !isWallRowBottomNext())
            moveImgDown();
        else
            getNewDirection();
    }

    @Override
    protected void changePos(JLabel lbl) {
        lbl.setIcon(getCurrImgDirection());
        Thread t = new Thread(this);
        t.start();
        changeImgForAnimation(lbl);
        lbl.setBackground(Color.BLACK);
    }

    protected void leftImgOperations() {
        if (currImgPosCol > 0 && !isWallCollLeftNext())
            moveImgLeft();
        else
            getNewDirection();
    }

    private void getNewDirection() {
        currDirection = changeImgRandDirection();
        currImgDirection = changeImgByDirection(currDirection);
        currImgAnimation = changeImgAnimationByDir(currDirection);
        imgOperations();
    }

    @Override
    int imgStartPosRow() {
        return 3;
    }

    @Override
    int imgStartPosCol() {
        return 1;
    }

}
