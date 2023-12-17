package src;

public class ButtonNewGame extends Button {

    public ButtonNewGame(String name) {
        super(name);
    }

    protected void setAction() {
        this.addActionListener(e -> {
            new WindowGameSize("width");
        });
    }
}
