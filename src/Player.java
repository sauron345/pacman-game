package src;
import java.io.Serializable;

public class Player implements Serializable {
    private final String username;
    private final int points;
    private final int playTime;

    public Player(String username, int points, int playTime) {
        this.username = username;
        this.points = points;
        this.playTime = playTime;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public int getPlayTime() {
        return playTime;
    }
}
