
public class ButtonNewGame extends Button {

    public ButtonNewGame(String name) {
        super(name);
    }

    protected void setAction() {
        button.addActionListener(e -> new WindowGameSize());
    }
}
