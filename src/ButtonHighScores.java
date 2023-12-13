package src;

public class ButtonHighScores extends Button {

    public ButtonHighScores(String name) {
        super(name);
    }

    protected void setAction() {
        this.addActionListener(e -> System.exit(0));
    }
}
