package src;

import javax.swing.*;

public class WindowGameSize {

    public WindowGameSize() {
        Integer gridSize = promptForGridSize();

        if (gridSize != null && gridSize >= 10 && gridSize <= 100)
            new WindowGame(gridSize, gridSize);
        else if (!(gridSize == null))
            JOptionPane.showMessageDialog(WindowStart.frame, "Nieprawidłowy rozmiar planszy. Wybierz wartość od 10 do 100.");
    }

    static Integer promptForGridSize() {
        String windowSize = JOptionPane.showInputDialog("Enter size of window (from 10 to 100):");
        try {
            if (windowSize != null)
                return Integer.parseInt(windowSize);
            return null;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
