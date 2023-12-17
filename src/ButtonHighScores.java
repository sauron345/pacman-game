package src;

public class ButtonHighScores extends Button {

    public ButtonHighScores(String name) {
        super(name);
    }

    protected void setAction() {
        this.addActionListener(e -> {
            WindowPlayersStats windowPlayersStats = new WindowPlayersStats();
            windowPlayersStats.display();
        });
    }
}
