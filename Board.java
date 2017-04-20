import edu.princeton.cs.algs4.*;
import java.util.*;
import java.lang.*;

public class Board {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private int[][] board;
    private int sideSize;

    public Board(int[][] tiles) {
        sideSize = tiles.length;
        board = new int[sideSize][sideSize];
        for (int row = 0; row < sideSize; row++) {
            for (int col = 0; col < sideSize; col++) {
                board[row][col] = tiles[row][col];
            }
        }
    }

    public int tileAt(int row, int col) {
        if (row >= sideSize || row < 0 || col < 0 || col >= sideSize)
            throw new IndexOutOfBoundsException("Given coordinates do not correspond to any board tile.");
        return board[row][col];
    }

    public int size() {
        return sideSize;
    }

    public int hamming() {
        int sum = 0;
        for (int row = 0; row < sideSize; row++) {
            for (int col = 0; col < sideSize; col++) {
                if (board[row][col] != 0 && board[row][col] - 1 != (row * sideSize) + col)
                    sum++;
            }
        }
        return sum;
    }

    public int manhattan() {
        int sum = 0, man = 0, i, j, num;
        for (int row = 0; row < sideSize; row++) {
            for (int col = 0; col < sideSize; col++) {
                num = board[row][col];
                if (num != 0 ) {
                    i = (num - 1)/sideSize;
                    j = (num - 1) % sideSize;
                    man = Math.abs(row - i) + Math.abs(col - j);
                    sum += man;
                }
            }
        }
        return sum;
    }

    public boolean isGoal() {
        return false;
    }

    public boolean isSolvable() {
        return false;
    }

    public boolean equals(Object y) {
        boolean equal = true;
        try {
            Board tmp = (Board) y;
            for (int row = 0; row < sideSize; row++) {
                for (int col = 0; col < sideSize; col++) {
                    if (board[row][col] != tmp.tileAt(row,col))
                        equal = false;
                }
            }
        }
        catch (Exception e) {
            equal = false;
        }
        return equal;
    }

    public Iterable<Board> neighbors() {
        return new Iterable<Board> () {
            public Iterator<Board> iterator() {
                return new Iterator<Board> () {
                    public boolean hasNext() {
                        return false;
                    }
                    public Board next() {
                        return null;
                    }
                    public void remove(){
                    }
                };
            }
        };
    }


    public String toString(){
        String str = Integer.toString(sideSize) + "\n";
            for (int row = 0; row < sideSize; row++) {
                for (int col = 0; col < sideSize; col++) {
                    str += board[row][col] + "\t";
                }
                str += "\n";
            }
        return str;
    }

    public static void main(String[] args) {

        StdOut.println("==============TESTING CLASS BOARD===============");
        int[][] testBoard = {{5,1,6},{4,2,8},{7,0,3}};
        Board b = new Board(testBoard);
        Board b2 = new Board(testBoard);
        StdOut.print("The newly created Board must be size 3...");
        if (b.size() == 3) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        boolean valid = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b.tileAt(i, j) != testBoard[i][j]) {
                    valid = false;
                }
            }
        }
        StdOut.print("Tsting Board consistency.................");
        if (valid) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

        StdOut.print("Testing hamming..........................");
        if (b.hamming() == 6) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET + "EX:6 Got:" + b.hamming());

        StdOut.print("Testing isGoal...........................");
        if (!b.isGoal()) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

        StdOut.print("Testing manhattan........................");
        if (b.manhattan() == 9) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

        StdOut.print("Testing isSolvable.......................");
        if (b.isSolvable()) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

        StdOut.print("Testing equals...........................");
        if (b.equals(b2)) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

        StdOut.print("Testing toString......................... \n" + b + "\n");
        if (b.toString().equals("5\t1\t6\n4\t2\t8\n7\t0\t3\n")) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

        //fazer testes do iterable
        StdOut.print("Testing Iterable.........................");
        if (false) StdOut.println(ANSI_GREEN + "NOTEST" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "NOTEST" + ANSI_RESET);

    }

}
