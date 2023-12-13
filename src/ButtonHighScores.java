public class ButtonHighScores extends Button {

    public ButtonHighScores(String name) {
        super(name);
    }

    protected void setAction() {
        button.addActionListener(e -> System.exit(0));
    }
}
