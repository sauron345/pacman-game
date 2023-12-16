package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ImagePacman extends Image implements KeyListener {
    private final int countHearts = 3;
    int availableHearts = countHearts;
    ImageIcon imgHeart;

    public ImagePacman(WindowGame windowPacmanGame) {
        super(windowPacmanGame);
        imgsConfig();
        imgsDirections();
        imgsAnimations();
    }

    private void imgsConfig() {
        currImgDirection = new ImageIcon("images/pacman/left.png");
        currImgAnimation = new ImageIcon("images/pacman/animation/left.png");
        imgHeart = new ImageIcon("images/others/heart.png");
    }

    @Override
    protected void imgsDirections() {
        leftImg = new ImageIcon("images/pacman/left.png");
        rightImg = new ImageIcon("images/pacman/right.png");
        downImg = new ImageIcon("images/pacman/down.png");
        upImg = new ImageIcon("images/pacman/up.png");
    }

    @Override
    protected void imgsAnimations() {
        leftAnim = new ImageIcon("images/pacman/animation/left.png");
        rightAnim = new ImageIcon("images/pacman/animation/right.png");
        downAnim = new ImageIcon("images/pacman/animation/down.png");
        upAnim = new ImageIcon("images/pacman/animation/up.png");
    }

    @Override
    protected void changePos(JLabel lbl) {
        lbl.setIcon(getCurrImgDirection());
        changeImgForAnimation(lbl);
        lbl.setBackground(Color.BLACK);
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

    protected void leftImgOperations() {
        changeCurrImgs("left");
        if (currImgPosCol > 0 && !isWallCollLeftNext())
            moveImgLeft();
        else
            changeImgOnly();
    }

    private void changeCurrImgs(String direction) {
        currImgDirection = changeImgByDirection(direction);
        currImgAnimation = changeImgAnimationByDir(direction);
    }

    protected void rightImgOperations() {
        changeCurrImgs("right");

        if (currImgPosCol < getTableRightBorder() && !isWallCollRightNext())
            moveImgRight();
        else
            changeImgOnly();
    }

    protected void topImgOperations() {
        changeCurrImgs("up");

        if (currImgPosRow > 0 && !isWallRowTopNext())
            moveImgUp();
        else
            changeImgOnly();
    }

    protected void bottomImgOperations() {
        changeCurrImgs("down");

        if (currImgPosRow < getTableBottomBorder() && !isWallRowBottomNext())
            moveImgDown();
        else
            changeImgOnly();
    }


    private void changeImgOnly() throws ErrorDirectionTypeException {
        windowGame.displayImgInDifferentPos(this);
    }

    public void reduceHealth() {
        availableHearts--;
    }

    @Override
    int imgStartPosRow() {
        return 2;
    }

    @Override
    int imgStartPosCol() {
        return 7;
    }

}
