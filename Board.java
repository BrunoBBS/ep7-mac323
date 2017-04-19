import edu.princeton.cs.algs4.*;
import java.util.*;

public class Board {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private int[][] board;
    private int sideSize;

    public Board(int[][] tiles) {
        sideSize = tiles.length;
        board = new int[sideSize][sideSize];
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        return board[i][j];
    }

    public int size() {
        return sideSize;
    }

    public int hamming() {
        return 0;
    }

    public int manhattan() {
        return 0;
    }

    public boolean isGoal() {
        return false;
    }

    public boolean isSolvable() {
        return false;
    }

    public boolean equals(Object y) {
        return false;
    }

    public Iterable<Board> neighbors() {
        return new Iterable<Board> () {
            public Iterator<Board> iterator() {
                return null;
            }
            class BoardIterator<Board> implements Iterator<Board> {
                public boolean hasNext() {
                    return false;
                }
                public Board next() {
                    return null;
                }
                public void remove(){

                }
            }
        };
    }


    public String toString(){
        return "heyhey";
    }

    public static void main(String[] args) {

        StdOut.println("TESTING CLASS BOARD");
        int[][] testBoard = {{5,1,6},{4,2,8},{7,0,3}};
        Board b = new Board(testBoard);
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
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
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
        if (b.equals(testBoard)) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Testing toString.........................");
        if (b.toString().equals("5\t1\t6\n4\t2\t8\n7\t0\t3\n")) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        //fazer testes do iterable
        StdOut.print("Testing Iterable.........................");
        if (b.hamming() == 6) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

    }

}
