import java.util.Scanner;

public class TicTacToe
{
    /**
     * Constructor for objects of class tictactoe
     */
    public TicTacToe()
    {
        // We don't need to do anything in the constructor for
        // our program.
    }

    public static void main(String[] args) {
        boolean playAgain = true;
        while (playAgain) {
            Scanner input = new Scanner(System.in);
            String playerOne;
            String playerTwo;
            System.out.println("Hello Players");
            System.out.println("Enter Player one's name");
            playerOne = input.next();
            System.out.println("Enter Player two's name");
            playerTwo = input.next();
            System.out.println("Connect-Four:" + playerOne + " vs "+ playerTwo);

            char[][] board = new char[6][7];
            for(int row=0; row<6;row++){
                for (int col=0; col<7;col++){
                    board[row][col]='-';
                }
            }

            boolean playerOneTurn = true;
            boolean gameEnded = false;
            while(!gameEnded) {
                drawBoard(board);
                char symbol = ' ';
                if (playerOneTurn) {
                    symbol = 'B';
                } else {
                    symbol = 'R';
                }

                if (playerOneTurn) {
                    System.out.println(playerOne + "'s turn (B)");
                } else {
                    System.out.println(playerTwo + "'s turn (R)");
                }
                int columnMove = input.nextInt()-1;
                while (true) {
                    System.out.println("Select a column (1-6)");
                    isColFull(board, input, symbol, playerOneTurn);
                    break;
                }
                for (int row = 0; row < 6; row++ ) {
                    if (hasWon(board) == 'B') {
                        System.out.println(playerOne + " has won!");
                        gameEnded = true;
                    } else if (hasWon(board) == 'R') {
                        System.out.println(playerTwo + " has won!");
                        gameEnded = true;
                    }else{
                        if (hasTied(board)) {
                            System.out.println("You guys have tied");
                            gameEnded = true;
                        } else {
                            playerOneTurn = !playerOneTurn;
                        }
                    }

                }
            }
            drawBoard(board);

            System.out.println("If you want to play again, type Yes, else type No");
            if (input.next().equals("Yes")) {
                playAgain = true;
            } else if (input.next().equals("No")) {
                playAgain = false;
                gameEnded = true;
            } else {
                playAgain = false;
                gameEnded = true;
            }

        }
    }

    public static void drawBoard(char[][] board) {
        for(int row=0; row<6;row++){
            for (int col=0; col<7;col++){
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    public static char hasWon(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0]!= '-') {
                return board[i][0];
            }
        }

        for (int k = 0; k < 3; k++) {
            if (board[0][k] == board[1][k] && board[1][k] == board[2][k] && board[0][k] != '-') {
                return board[0][k];
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }

        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }
        return '-';
    }

    public static boolean hasTied(char[][] board) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static char isColFull(char[][] board, Scanner input, char symbol, boolean playerOneTurn){
        // which column did player pick?
        int columnMove = input.nextInt()-1;
        // check if top row is filled up
        if (columnMove < 1 || columnMove > 7) {
            System.out.println("Please select a valid column");
        } else {
            int row = 5;
            if (board[columnMove][row] == '-') {
                if (playerOneTurn) {
                    symbol = 'B';
                } else if (!playerOneTurn){
                    symbol = 'R';
                }
            } else if (board[columnMove][row] != '-') {
                row--;
            }
            board[row][columnMove] = symbol;
        }
        return symbol;
    }
}

