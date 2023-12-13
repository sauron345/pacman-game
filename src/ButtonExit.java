public class ButtonExit extends Button {
    public ButtonExit(String name) {
        super(name);
    }

    protected void setAction() {
        button.addActionListener(e -> System.exit(0));
    }

}
