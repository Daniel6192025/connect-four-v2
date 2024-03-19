import java.util.Scanner;

/**
 * Write a description of class connectFour here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class connectFour
{
    //global variables
    static Scanner scanner = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    //game board
    static char[][] board = new char[Constants.BOARD_ROW][Constants.BOARD_COL];

    public static void main(String[] args) {
        System.out.println("Welcome to Connect-Four!");
        System.out.println("Please enter your names");

        String p1 = scanner.next();
        String p2 = scanner.next();

        Player player1 = new Player(p1, Constants.P1_COLOR);
        Player player2 = new Player(p2, Constants.P2_COLOR);        

        connectFour play = new connectFour(player1, player2);
        play.run();
    }

    public connectFour (Player player1, Player player2) {
        CreateBoard();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void run(){
        boolean gamePlay = true;
        while(gamePlay){
            System.out.println("Connect-Four: " + player1.toString()  +   " vs " + player2.toString());
            printBoard();
            System.out.println("Player " + currentPlayer.toString() + "'s turn.");
            System.out.println("Select a column from 0-6");
            if (placePiece(currentPlayer.getPlayerMove())){
                if (!checkP1()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer.toString() + " wins!");
                    gamePlay = false;
                } else if (!checkP2()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer.toString() + " wins!");
                    gamePlay = false;
                } else if (hasTied(board)) {
                    printBoard();
                    System.out.println("Its a tie!");
                    gamePlay = false;
                } else {
                    switchPlayers();
                }
            }
        }
    }

    public void switchPlayers(){
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public static void CreateBoard() {
        //fills board with '.' for the width and height
        for (int row = 0; row<Constants.BOARD_ROW; row ++) {
            for (int col = 0; col<Constants.BOARD_COL; col ++) {
                board[row][col] = Constants.EMPTY;
            }
        }

    }

    public  void printBoard() {
        //prints the board
        for (int row = 0; row<Constants.BOARD_ROW; row ++) {
            for (int col = 0; col<Constants.BOARD_COL; col ++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean placePiece(int column){
        if (column < 0 || column >= Constants.BOARD_COL || board[0][column] != Constants.EMPTY) {
            System.out.println("Please choose a valid move");
            return false;
        }

        for (int row = Constants.BOARD_LOW; row >= 0; row--) {
            if (board[row][column] == Constants.EMPTY) {
                board[row][column] = currentPlayer.getColor();
                return true;
            }
        }
        return false;
    }

    public boolean checkHorizontalVertical(){
        boolean check = true;
        int p1Counter = 0;
        int p2Counter=0;
        int counter = 0;
        boolean flag=true;
        while(flag){

            //goes through board vertically
            for(int col = 0; col < Constants.BOARD_COL-1; col ++){
                for(int row = 0; row < Constants.BOARD_ROW-1; row ++){
                    if((board[col][row] == 'B')){ 
                        p1Counter++;
                    }else if(board[col][row]=='R'){
                        p2Counter++; 
                    }else{
                        p1Counter=0;
                        p2Counter=0;
                    }
                    if((p1Counter >= 4)|(p2Counter>=4)){
                        flag = false;
                    }
                }
            }
            for(int row = 0; row < Constants.BOARD_ROW-1; row ++){
                for(int col = 0; col < Constants.BOARD_COL-1; col ++){
                    if((board[col][row] == 'B')|(board[col][row]=='R')){ 
                        counter++;
                    }else{
                        counter = 0; 
                    }
                    if(counter >= 4){
                        flag = false;
                    }
                }
            }
            break;
        }

        return flag;
    }

    public boolean checkRightDiagonal(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        while(flag){ 
            for(int row = 0; row < Constants.BOARD_ROW-1; row ++){
                for(int col = 0; col < Constants.BOARD_COL-1; col ++){
                    if((board[col+1][row+1] == 'B')|(board[col][row]=='R')){ 
                        counter++;
                    }else{
                        counter = 0; 
                    }
                    if(counter >= 4){
                        flag = false;
                    }
                }
            }

        }
        return flag;
    }

    public boolean checkLeftDiagonal(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        while(flag){ //goes through until an O is found
            for(int row = 0; row < Constants.BOARD_ROW-1; row ++){
                for(int col = 0; col < Constants.BOARD_COL-1; col ++){
                    if((board[col-1][row-1] == 'B')|(board[col][row]=='R')){ 
                        counter++;
                    }else{
                        counter = 0; 
                    }
                    if(counter >= 4){
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public boolean checkP1(){
        //creates flag
        boolean flag = true;

        //checks all Hs at once, for clearner main loop
        if( checkHorizontalVertical() || checkLeftDiagonal() || checkRightDiagonal()){
            flag = false;
        }
        return flag;
    }

    public boolean checkP2(){
        //creates flag
        boolean flag = true;

        //checks all Os at once, for clearner main loop
        if( checkHorizontalVertical() || !checkLeftDiagonal() || checkRightDiagonal()){
            flag = false;
        }
        return flag;
    }

    public boolean hasTied(char[][] board) {
        for (int row = 0; row < Constants.BOARD_ROW; row++) {
            for (int col = 0; col < Constants.BOARD_COL; col++) {
                if (board[row][col] == Constants.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

