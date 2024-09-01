import java.util.Scanner;

public class Tic_Tac_Toe {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) throws Exception {
        System.out.println("You are Player X");
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        Scanner sc = new Scanner(System.in);
        boolean gameOver = false;
        int count = 0;

        while (!gameOver) {
            printBoard(board);

            if (count % 2 == 0) { // Player X's turn
                int row, col;
                while (true) {
                    System.out.println("Enter your move (row and column): ");
                    row = sc.nextInt();
                    col = sc.nextInt();
                    if (row > 2 || col > 2 || board[row][col] != ' ') {
                        System.out.println(ANSI_RED + "Invalid move! Try again." + ANSI_RESET);
                    } else {
                        break;
                    }
                }
                board[row][col] = 'X';
            } else { // Computer's turn (O)
                System.out.println("Computer is making a move...");
                int[] bestMove = findBestMove(board);
                board[bestMove[0]][bestMove[1]] = 'O';
            }

            count++;
            gameOver = hasWon(board, (count % 2 == 1) ? 'X' : 'O');
            if (gameOver) {
                printBoard(board);
                System.out.println();
                if (count % 2 == 1) {
                    System.out.println(ANSI_GREEN + "Winner is: " + ANSI_RESET + ANSI_RED + "X" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_GREEN + "Winner is: " + ANSI_RESET + ANSI_BLUE + "O" + ANSI_RESET);
                }
                break;
            } else if (count == 9) {
                System.out.println("Draw!");
                printBoard(board);
                break;
            }
        }
        sc.close();
    }

    private static int[] findBestMove(char[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int moveScore = minimax(board, false);
                    board[i][j] = ' ';
                    if (moveScore > bestScore) {
                        bestScore = moveScore;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int minimax(char[][] board, boolean isMaximizing) {
        if (hasWon(board, 'O')) {
            return 1;
        }
        if (hasWon(board, 'X')) {
            return -1;
        }
        if (isBoardFull(board)) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int score = minimax(board, false);
                        board[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int score = minimax(board, true);
                        board[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasWon(char[][] board, char player) {
        // Checking for the rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        // Checking for the columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        // Checking for the diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private static void printBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] != ' ') {
                    if (board[row][col] == 'X') {
                        System.out.print(ANSI_RED + " " + board[row][col] + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_BLUE + " " + board[row][col] + ANSI_RESET);
                    }
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
    }
}

