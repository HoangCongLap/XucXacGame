package xucxac.database.entites;

public class Dice {
    int idPhong;
    int numberInDice;
    boolean ClickInRoll;

    public int getIdPhong() {
        return idPhong;
    }

    public int getNumberInDice() {
        return numberInDice;
    }

    public boolean isClickInRoll() {
        return ClickInRoll;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public void setNumberInDice(int numberInDice) {
        this.numberInDice = numberInDice;
    }

    public void setClickInRoll(boolean clickInRoll) {
        ClickInRoll = clickInRoll;
    }

    public Dice(int idPhong, int numberInDice, boolean clickInRoll) {
        this.idPhong = idPhong;
        this.numberInDice = numberInDice;
        ClickInRoll = clickInRoll;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "idPhong=" + idPhong +
                ", numberInDice=" + numberInDice +
                ", ClickInRoll=" + ClickInRoll +
                '}';
    }
}
