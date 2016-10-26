import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Anusha on 10/15/2016.
 */
public class readInput {
    public int n;
    public String mode;
    public char player;
    public int depth;
    public int[][] boardValue;
    public char[][] boardState;



    public readInput(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.n = Integer.parseInt(bufferedReader.readLine());
            this.mode = bufferedReader.readLine();
            this.player = bufferedReader.readLine().toString().charAt(0);
            this.depth = Integer.parseInt(bufferedReader.readLine());
            this.boardValue = new int[n][n];
            this.boardState = new char[n][n];

            for (int i = 0; i < n; i++) {
                String line = bufferedReader.readLine();
                String[] split = line.split(" ");
                for (int j = 0; j < n; j++)
                    this.boardValue[i][j] = Integer.parseInt(split[j]);
            }

            for (int i = 0; i < n; i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < n; j++)
                    this.boardState[i][j] = line.charAt(j);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Unable to open file" + fileName);
        }
        catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}
