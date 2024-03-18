import java.util.Scanner;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    Scanner input = new Scanner(System.in);
    private String name;
    private char color;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name, char color)
    {
        this.color = color;
        this.name = name;
    }

    public char getColor() {
        return this.color;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getPlayerName()
    { 
        return this.name;
    }

    boolean isThisPlayer(Player p) {
        return p.getColor() == this.getColor();
    }

    boolean isThisPlayer(int p) {
        return p == this.getColor();
    }

    public int getPlayerMove() {
        int columnMove = input.nextInt();
        return columnMove;
    }

    public String toString() {
        switch (this.color) {
            case Constants.PLAYER_1:
                return this.getPlayerName() + " (BLUE)";
            case Constants.PLAYER_2:
                return this.getPlayerName()  + " (RED)";
            default:
                return this.getPlayerName() + " (?????)";

        }
    }
}
