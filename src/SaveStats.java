package src;
import javax.swing.*;
import java.io.*;

public class SaveStats {
    private String filename = "playersStats/player1.bin";
    private Player player;

    public SaveStats(WindowGame windowGame) {
        windowGame.dispose();
        String enteredUsername = showWindowInput();

        if (enteredUsername != null) {
            int collectedPoints = windowGame.getPoint().getCollectedPoints();
            int playTime = windowGame.getTimeUpdater().getPlayTime();

            saveStats(enteredUsername, collectedPoints, playTime);

            new WindowStart().display();
        } else
            JOptionPane.showMessageDialog(windowGame,"Entered username is not valid");
    }

    public static String showWindowInput() {
        String username = JOptionPane.showInputDialog("Enter your username to save your stats");
        try {
            if (username != null)
                return username;
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void saveStats(String username, int points, int playTime) {
        File f;
        int i = 1;
        while (true) {
            f = new File(filename);
            if (f.exists() && !f.isDirectory())
                filename = "playersStats/player"+(++i)+".bin";
            else
                break;
        }

        player = new Player(username, points, playTime);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename, true));
            outputStream.writeObject(player);
            outputStream.close();
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}