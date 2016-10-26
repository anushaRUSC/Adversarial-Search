import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Anusha on 10/14/2016.
 */
public class AlphaBeta {

    public static int minPlayer(BoardState b, int alpha, int beta, int depth) {
        boolean ret=false;
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length ; j++) {
                if(b.board[i][j] == '.'){
                    ret=true;
                }
            }

        }

        if(ret==false)
            return b.boardValue();

        if(depth==0){
            int val= b.boardValue();
            return val;
        }
        int v = Integer.MAX_VALUE;
        LinkedList<Actions> a = Actions.legalActions(b);

        for(Iterator<Actions> iter = a.iterator();iter.hasNext();){
            Actions item = iter.next();
            v=Math.min(v,maxPlayer(BoardState.Result(b,item),alpha,beta,depth-1));
            if(v<=alpha) return v;
            beta=Math.min(beta,v);
        }
        return v;
    }

    public static int maxPlayer(BoardState b,int alpha, int beta, int depth){
        boolean ret=false;
        for (int i = 0; i < b.board.length; i++) {
            for (int j = 0; j < b.board.length ; j++) {
                if(b.board[i][j] == '.'){
                    ret=true;
                }
            }

        }

        if(ret==false)
            return b.boardValue();

        if(depth==0){
            int val= b.boardValue();
            return val;
        }

        int v = Integer.MIN_VALUE;
        LinkedList<Actions> a = Actions.legalActions(b);

        for(Iterator<Actions> iter = a.iterator(); iter.hasNext();) {
            Actions item = iter.next();
            v=Math.max(v,minPlayer(BoardState.Result(b,item),alpha,beta,depth-1));
            if(v>=beta) return v;
            alpha=Math.max(alpha,v);
        }
        return v;
    }
}
