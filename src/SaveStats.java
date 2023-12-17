package src;
import javax.swing.*;

public class SaveStats {

    public SaveStats(WindowGame windowGame) {
        windowGame.dispose();
        String gridSize = showWindowInput();
        if(gridSize != null) {
//            Ranking ranking = loadRanking("playersStats.txt");
//            Ranking ranking = new Ranking();
//            ranking.saveStats("playersStats.txt");
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

}