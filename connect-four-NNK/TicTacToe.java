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
            System.out.println("Tic-Tac-Toe:" + playerOne + " vs "+ playerTwo);

            char[][] board = new char[3][3];
            for(int i=0; i<3;i++){
                for (int k=0; k<3;k++){
                    board[i][k]='-';
                }
            }

            boolean playerOneTurn = true;
            boolean gameEnded = false;
            while(!gameEnded) {
                drawBoard(board);
                char symbol = ' ';
                if (playerOneTurn) {
                    symbol = 'o';
                } else {
                    symbol = 'x';
                }

                if (playerOneTurn) {
                    System.out.println(playerOne + "'s turn (x)");
                } else {
                    System.out.println(playerTwo + "'s turn (o)");
                }

                int row = 0;
                int column = 0;

                while (true) {
                    System.out.println("Select on vertical axis (1, 2, or 3): ");
                    row = input.nextInt()-1;
                    System.out.println("Select on horizontal axis (1, 2, or 3): ");
                    column = input.nextInt()-1;

                    //Row and Column authenticator
                    if (row < 0 || column < 0 || row > 2 || column > 2) {
                        System.out.println("Out of bounds");
                    } else if (board[row][column] != '-') {
                        System.out.println("Spot already selected");
                    } else {
                        break;
                    }
                }
                board[row][column] = symbol;

                if (hasWon(board) == 'x') {
                    System.out.println(playerOne + " has won!");
                    gameEnded = true;
                } else if (hasWon(board) == 'o') {
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
            drawBoard(board);

            System.out.println("If you want to play again, type Yes, else type No");
            if (input.next().equals("Yes")) {
                playAgain = true;
            } else {
                if (input.next().equals("No")) {
                    playAgain = false;
                    gameEnded = true;
                } else {
                    gameEnded = true;
                }

            }
        }
    }

    public static void drawBoard(char[][] board) {
        for(int i=0; i<3;i++){
            for (int k=0; k<3;k++){
                System.out.print(board[i][k]);
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
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (board[i][k] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}


