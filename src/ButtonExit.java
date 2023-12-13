package src;

public class ButtonExit extends Button {
    public ButtonExit(String name) {
        super(name);
    }

    protected void setAction() {
        this.addActionListener(e -> System.exit(0));
    }

}
