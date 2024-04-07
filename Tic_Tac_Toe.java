package Projects;

import java.util.Scanner;

public class Tic_Tac_Toe {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static void main(String[] args) throws Exception{
        System.out.println("First Move entered by :Player X");
         char [][] board = new char[3][3];
         for(int i=0;i<3;i++){
             for(int j=0;j<3;j++){
                 board[i][j]=' ';
             }
         }
         boolean flag=false;
         char player='X';
         boolean gameOver=false;
        Scanner sc= new Scanner(System.in);
        int count=0;
        while(!gameOver){

            printboard(board);
            int row=sc.nextInt();
            int col= sc.nextInt();
            if(row>2 || col>2){
                System.out.println(ANSI_RED + "Error: You can't give value that is greater than" +
                        " Board size ! Give the value between 0-2" + ANSI_RESET);
            }
             else{
                count++;
                System.out.println("Player " +player +" entered here");
                if(board[row][col]==' '){
                    board[row][col]=player;
                    gameOver=hasWon(board,player);
                    if(gameOver){
                        flag=true;
                        printboard(board);
                        System.out.println();
                        if(player=='X'){
                            System.out.println(ANSI_GREEN + "Winner is: " + ANSI_RESET+ANSI_RED+"X"+ANSI_RESET);
                        }else{
                            System.out.println(ANSI_GREEN + "Winner is: " + ANSI_RESET+ANSI_BLUE+"0"+ANSI_RESET);
                        }



                    }
                    else{
                        if(player=='X'){
                            player='0';
                        }
                        else {
                            player='X';
                        }
                    }
                    if(count==9 && !flag){
                        System.out.println("draw!");
                        break;
                    }
                }
                else{
                    System.out.println("Invalid move !Try again please.");
                    count--;
                }
            }
        }
    }

    private static boolean hasWon(char[][] board, char player) {
        //checking for the rows
            for(int row=0;row<3;row++){
                if(board[row][0]==player && board[row][1]==player && board[row][2]==player){
                    return true;

                }
            }
            //checking for the columns
        for(int col=0;col<3;col++){
            if(board[0][col]==player && board[1][col]==player && board[2][col]==player){
                return true;
            }
        }
        // checking for the diagonals
        if(board[0][0]==player && board[1][1]==player && board[2][2]==player){
            return true;
        }
        if(board[0][2]==player && board[1][1]==player && board[2][0]==player){
            return true;
        }
        return false;
    }

    private static void printboard(char [][] board) {
            for(int row=0;row<3;row++){
                for(int col=0;col<3;col++){
                    if(board[row][col]!=' '){
                        if(board[row][col] == 'X'){
                            System.out.print(ANSI_RED + " "+board[row][col] + ANSI_RESET);
                        }
                        else {
                            System.out.print(ANSI_BLUE + " "+board[row][col]  + ANSI_RESET);
                        }
                    }
                    else{
                        System.out.print(board[row][col]+"|");
                    }
                }
                System.out.println();
            }
    }
}
