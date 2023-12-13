package src;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void centerWindowToScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    public static void main(String[] args) {
        WindowStart mainWindow = new WindowStart();
        mainWindow.display();
    }

}
