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
        int counter = 0;
        int up=0;
        boolean flag=true;
        while(flag){

            //goes through board vertically
            for(int col = 0; col < Constants.BOARD_COL; col ++){
                for(int row = 0; row < Constants.BOARD_ROW; row ++){
                    if((board[col][row] == 'B')){ 
                        counter++;
                    }else if(board[col][row]=='R'){
                        up++; 
                    }else{
                        counter=0;
                        up=0;
                    }
                    if((counter >= 4)|(up>=4)){
                        flag = false;
                    }
                }
            }
            for(int col = 0; col < Constants.BOARD_COL; col ++){
                for(int row = 0; row < Constants.BOARD_ROW; row ++){
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

    public boolean CheckHDiagonal(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ 
            for(int col = 0; Constants.BOARD_COL > col; col ++){
                for(int row = 0;  Constants.BOARD_ROW>row; row ++){
                    if(board[col][row] == 'B'){ 
                        counter += 1;
                        check = true;
                        while(check){ 
                            if(checkColumn + col <= Constants.BOARD_COL - 1&& checkRow + row <= Constants.BOARD_ROW - 1){
                                if(board[col + checkColumn][row + checkRow] == 'B'){
                                    counter += 1;
                                }
                            }

                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == Constants.BOARD_COL -1 || checkRow == Constants.BOARD_ROW -1){ 
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); 
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        boolean look=true;
        while(flag){ //goes through until an X is found
            for(int col = 0; Constants.BOARD_COL > col; col += 1){
                for(int row = 0; Constants.BOARD_ROW > row; row += 1){
                    if(board[col][row] == 'B'){ //if X is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Xs
                            if(col - checkColumn >= 0 && row - checkRow >= 0){
                                if(board[col - checkColumn][row - checkRow] == 'B'){
                                    counter += 1; //if X is found, add 1 to counter
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == Constants.BOARD_ROW -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;

    }

    public boolean CheckODiagonal(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an O is found
            for(int col = 0; Constants.BOARD_COL > col; col += 1){
                for(int row = 0; Constants.BOARD_ROW > row; row += 1){
                    if(board[col][row] == 'R'){ //if O is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Os
                            if(checkColumn + col <= Constants.BOARD_COL - 1&& checkRow + row <= Constants.BOARD_ROW - 1){
                                if(board[col + checkColumn][row + checkRow] == 'R'){ //if O is found, add 1 to counter
                                    counter += 1;
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == Constants.BOARD_COL -1 || checkRow == Constants.BOARD_ROW -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        while(flag){

            //goes through until an O is found
            for(int col = 0; Constants.BOARD_COL > col; col += 1){
                for(int row = 0; Constants.BOARD_ROW > row; row += 1){
                    if(board[col][row] == 'R'){ //if O is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Os
                            if(col - checkColumn >= 0 && row - checkRow >= 0){
                                if(board[col - checkColumn][row - checkRow] == 'R'){
                                    counter += 1; //if O is found, add 1 to counter
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == Constants.BOARD_ROW -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean checkP1(){
        //creates flag
        boolean flag = true;

        //checks all Hs at once, for clearner main loop
        if( !checkHorizontalVertical()|| !CheckHDiagonal()){
            flag = false;
        }
        return flag;
    }

    public boolean checkP2(){
        //creates flag
        boolean flag = true;

        //checks all Os at once, for clearner main loop
        if( !checkHorizontalVertical() || !CheckODiagonal()){
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

