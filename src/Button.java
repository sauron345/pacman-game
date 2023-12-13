package src;

import javax.swing.*;
import java.awt.*;

abstract public class Button extends JButton {
    protected Color backgroundColor = WindowStart.getComponentsColor();
    protected Color foregroundColor = WindowStart.getComponentsColor();

    public Button(String name) {
        setText(name);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setContentAreaFilled(false);
        setFocusable(false);
        setAction();
    }

    abstract protected void setAction();

    public JButton getJButton() {
        return this;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }
}
