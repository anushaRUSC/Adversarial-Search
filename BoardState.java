import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Anusha on 10/7/2016.
 */
public class BoardState {
    public char[][] board;
    public int[][] boardVal;
    char player;
    char initPlayer;

    public BoardState(int N, char[][] b,char p,int [][] bVal, char c) {

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = b[i][j];
            }
        }
        player=p;
        initPlayer=c;
        boardVal = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boardVal[i][j] = bVal[i][j];
            }
        }
    }

    public BoardState(BoardState b) {
        int len=b.board.length;
        board = new char[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                board[i][j] = b.board[i][j];
            }
        }
        player=b.player;
        initPlayer=b.initPlayer;
        boardVal = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                boardVal[i][j] = b.boardVal[i][j];
            }
        }
    }

    public void PrintBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }


    public static BoardState Result(BoardState b, Actions a){
        if(a.move.equals("Stake")){
            BoardState temp = new BoardState(b);
            temp.board[a.row][a.col]=b.player;
            if(b.player=='X') temp.player='O';
            else temp.player='X';
            return temp;
        }
        else if(a.move.equals("Raid")){
            BoardState temp = new BoardState(b);
            temp.board[a.row][a.col]=b.player;
            BoardState.conquer(a.row,a.col,temp);
            if(b.player=='X') temp.player='O';
            else temp.player='X';
            return temp;
        }
        return null;
    }

    public int boardValue(){

            char opponent=',';
            char initPlayer=this.initPlayer;
            int initPlayerSum=0;
            int opponentSum=0;
            if(initPlayer=='X') opponent='O';
            if(initPlayer=='O') opponent='X';

            for (int i = 0; i < this.boardVal.length ; i++) {
                for (int j = 0; j < this.boardVal.length; j++) {
                    if(this.board[i][j]==initPlayer)
                        initPlayerSum+=this.boardVal[i][j];
                    if(this.board[i][j] == opponent )
                        opponentSum+=this.boardVal[i][j];
                }
            }
            return (initPlayerSum-opponentSum);
    }

    public static boolean isValid(int x,int y, int N){
        if(x<N && y<N && x>=0 && y>=0) return true;
        return false;
    }

    public static void conquer(int x,int y, BoardState b){

        char opponent=',';
        if(b.player=='X') opponent='O';
        if(b.player=='O') opponent='X';
        if(isValid(x+1,y,b.board.length) && b.board[x+1][y]==opponent)
            b.board[x+1][y]=b.player;
        if(isValid(x-1,y,b.board.length) && b.board[x-1][y]==opponent)
            b.board[x-1][y]=b.player;
        if(isValid(x,y+1,b.board.length) && b.board[x][y+1]==opponent)
            b.board[x][y+1]=b.player;
        if(isValid(x,y-1,b.board.length) && b.board[x][y-1]==opponent)
            b.board[x][y-1]=b.player;
    }
}
