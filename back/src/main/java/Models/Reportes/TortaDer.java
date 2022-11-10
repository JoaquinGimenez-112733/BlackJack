package Models.Reportes;

public class TortaDer {
    private float win;
    private float lose;
    private float tie;

    public TortaDer(float win, float lose, float tie) {
        this.win = win;
        this.lose = lose;
        this.tie = tie;
    }

    public float getWin() {
        return win;
    }

    public void setWin(float win) {
        this.win = win;
    }

    public float getLose() {
        return lose;
    }

    public void setLose(float lose) {
        this.lose = lose;
    }

    public float getTie() {
        return tie;
    }

    public void setTie(float tie) {
        this.tie = tie;
    }
}
