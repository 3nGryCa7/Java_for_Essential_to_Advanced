package TicTacToe;

public class TicTacToe {


    private String nought = "O";
    private String crosses = "X";
    private String place;
    
    public TicTacToe() {
        this.place = " ";
    };

    public void setNought() {
        this.place = this.nought;
    }

    public void setCrosses() {
        this.place = this.crosses;
    }

    public String getPlace() {
        return this.place;
    }

}
