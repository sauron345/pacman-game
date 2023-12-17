package src;
import static src.WindowStart.frame;
import javax.swing.*;

public class WindowGameSize {
    private Integer enteredSize;
    private String sizeType;

    public WindowGameSize(String sizeType) {
        this.sizeType = sizeType;
        enteredSize = promptForGridSize();

        if (isEnteredSizeCorr()) {
            if (sizeType.equals("width")) {
                WindowGameSize gameSize = new WindowGameSize("height");
                if (gameSize.isEnteredSizeCorr()) {
                    new WindowGame(enteredSize, gameSize.getEnteredSize());
                    frame.dispose();
                }
            }
        } else if (!(enteredSize == null))
            JOptionPane.showMessageDialog(WindowStart.frame, "Invalid " + this.sizeType + " of window. Choose between 10 and 100");
    }

    public boolean isEnteredSizeCorr() {
        return enteredSize != null && enteredSize >= 10 && enteredSize <= 100;
    }

    private Integer promptForGridSize() {
        String windowSize = JOptionPane.showInputDialog("Enter " + sizeType + " of window (from 10 to 100):");
        try {
            if (windowSize != null)
                return Integer.parseInt(windowSize);
            return null;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Integer getEnteredSize() {
        return enteredSize;
    }
}
