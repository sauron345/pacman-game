import javax.swing.*;
import java.awt.*;

abstract public class Button {
    protected JButton button;
    protected Color backgroundColor = WindowStart.getComponentsColor();
    protected Color foregroundColor = WindowStart.getComponentsColor();

    public Button(String name) {
        button = new JButton(name);
        button.setText(name);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        setAction();
    }

    abstract protected void setAction();

    public JButton getJButton() {
        return button;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }
}
