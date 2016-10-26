import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class homework {

    public static void main(String args[]) throws IOException {

        for (int count = 0; count < 100; count++) {

            System.out.println("Testcase no: " + count);

            String file = "C:\\Users\\Anusha\\Desktop\\AdversarialSearch\\testcases\\" + count + ".in";
			String opfile = "C:\\Users\\Anusha\\Desktop\\AdversarialSearch\\MyOutput\\" + count + ".out";

            readInput input = new readInput(file);
            BoardState initBoard = new BoardState(input.n, input.boardState, input.player, input.boardValue,input.player);
            if (input.mode.equals("MINIMAX")) {
                LinkedList<Actions> initMoves = Actions.legalActions(initBoard);
                int v = Integer.MIN_VALUE;
                Actions resAction = new Actions();
                for (Iterator<Actions> iter = initMoves.iterator(); iter.hasNext(); ) {
                    Actions item = iter.next();
                    int res = MiniMax.minPlayer(BoardState.Result(initBoard, item), input.depth - 1);
                    if (res > v) {
                        v = res;
                        resAction.row = item.row;
                        resAction.col = item.col;
                        resAction.move = new String(item.move);
                    }
                }

                PrintWriter w = new PrintWriter(opfile);
                char x = (char) ('A' + resAction.col);
                w.print(x);
                w.print((resAction.row + 1));
                w.print(" " + resAction.move);
                w.println();
                BoardState opBoard = BoardState.Result(initBoard, resAction);
                for (int i = 0; i < opBoard.board.length; i++) {
                    for (int j = 0; j < opBoard.board.length; j++) {
                        w.print(opBoard.board[i][j]);
                    }
                    w.print(System.getProperty("line.separator"));

                }
                w.close();
            }

            if (input.mode.equals("ALPHABETA")) {
                int alpha = Integer.MIN_VALUE;
                int beta = Integer.MAX_VALUE;
                int v = Integer.MIN_VALUE;

                LinkedList<Actions> initMoves = Actions.legalActions(initBoard);

                Actions resAction = new Actions();
                for (Iterator<Actions> iter = initMoves.iterator(); iter.hasNext(); ) {
                    Actions item = iter.next();
                    int res = AlphaBeta.minPlayer(BoardState.Result(initBoard, item), alpha, beta, input.depth - 1);

                    if (res > v) {
                        v = res;
                        resAction.row = item.row;
                        resAction.col = item.col;
                        resAction.move = new String(item.move);
                    }
                }

                PrintWriter w = new PrintWriter(opfile);
                char x = (char) ('A' + resAction.col);
                w.print(x);
                w.print((resAction.row + 1));
                w.print(" " + resAction.move);
                w.println();
                BoardState opBoard = BoardState.Result(initBoard, resAction);
                for (int i = 0; i < opBoard.board.length; i++) {
                    for (int j = 0; j < opBoard.board.length; j++) {
                        w.print(opBoard.board[i][j]);
                    }
                    w.print(System.getProperty("line.separator"));

                }
                w.close();
            }
        }
    }
}
