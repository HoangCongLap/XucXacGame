package xucxac.database.entites;

public class LimitPlayer {
    private int player;

    public LimitPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "LimitPlayer{" +
                "player=" + player +
                '}';
    }
}
