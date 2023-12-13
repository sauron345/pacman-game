package src;

import javax.swing.*;
import java.awt.*;

public class WindowStart extends JPanel {
    private final int screenWidth = 400;
    private final int screenHeight = 300;
    public static final JFrame frame = new JFrame("Pacman");
    private final JPanel panel = new JPanel();
    private final JLabel title = new JLabel("Pacman");
    private static final Color backgroundColor = Color.BLACK;
    private static final Color componentsColor = Color.YELLOW;
    private Button newGameButton = new ButtonNewGame("New game");
    private Button highScoresButton = new ButtonHighScores("High Scores");
    private Button exitButton = new ButtonExit("Exit");

    public WindowStart() {
        frameConfig();
        titleConfig();

        centerPanelConfig();
    }

    private void frameConfig() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        Main.centerWindowToScreen(frame);
    }

    private void titleConfig() {
        title.setForeground(componentsColor);
        title.setHorizontalAlignment(JLabel.CENTER);

        int labelWidth = title.getPreferredSize().width;
        int frameWidth = frame.getWidth();
        int x = (frameWidth - labelWidth) / 2;
        int y = 30;
        title.setBounds(x, y, labelWidth, 30);
        frame.add(title);
    }

    public void display() {
        addButtons();
        frame.add(panel);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    private void centerPanelConfig() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
    }

    private void addButtons() {
        panel.add(Box.createVerticalGlue());

        panel.add(newGameButton.getJButton());

        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Odstęp pomiędzy przyciskami

        panel.add(highScoresButton.getJButton());

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(exitButton.getJButton());

        panel.add(Box.createVerticalGlue());
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static Color getComponentsColor() {
        return componentsColor;
    }

}
