import java.util.LinkedList;

/**
 * Created by Anusha on 10/9/2016.
 */
public class Actions {
    int row;
    int col;
    String move;

    public Actions(){
        this.row=0;
        this.col=0;
        this.move=null;
    }

    public  Actions(int x, int y, String s){
        this.row=x;
        this.col=y;
        this.move=s;
    }

    public static void printActions(Actions x){
        System.out.println("Row: " + x.row);
        System.out.println("Col: " + x.col);
        System.out.println("Move: " + x.move);
    }

    public static LinkedList<Actions> legalActions(BoardState bs){
        LinkedList<Actions> list = new LinkedList<Actions>();

        for (int i = 0; i <bs.board.length ; i++)
            for (int j = 0; j <bs.board[0].length ; j++){

                if (bs.board[i][j] == '.') {
                    Actions item1 = new Actions(i, j,"Stake");
                    list.add(item1);
                }
            }

        for (int i = 0; i <bs.board.length ; i++) {
            for (int j = 0; j < bs.board.length; j++) {
                if(bs.board[i][j]=='.'){

                    if(BoardState.isValid(i-1,j,bs.board.length) && bs.board[i-1][j]==bs.player){
                        Actions item2 = new Actions(i, j,"Raid");
                        list.add(item2);
                    }

                    if(BoardState.isValid(i+1,j,bs.board.length) && bs.board[i+1][j]==bs.player){
                        Actions item3 = new Actions(i, j,"Raid");
                        list.add(item3);
                    }

                    if(BoardState.isValid(i,j-1,bs.board.length) && bs.board[i][j-1]==bs.player){
                        Actions item4 = new Actions(i, j,"Raid");
                        list.add(item4);
                    }

                    if(BoardState.isValid(i,j+1,bs.board.length) && bs.board[i][j+1]==bs.player){
                        Actions item5 = new Actions(i, j,"Raid");
                        list.add(item5);
                    }
                }

            }

        }


        return list;
    }
}
