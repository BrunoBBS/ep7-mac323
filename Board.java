import edu.princeton.cs.algs4.*;
import java.util.*;
import java.lang.*;

public class Board {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private int[][] board;
    private int sideSize;
    private int manhattan_cache;

    public Board(int[][] tiles) {
        sideSize = tiles.length;
        manhattan_cache = -1;
        board = new int[sideSize][sideSize];
        for (int row = 0; row < sideSize; row++) {
            for (int col = 0; col < sideSize; col++) {
                board[row][col] = tiles[row][col];
            }
        }
    }

    // Returns the tile at that location
    public int tileAt(int row, int col) {
        if (row >= sideSize || row < 0 || col < 0 || col >= sideSize)
            throw new IndexOutOfBoundsException("Given coordinates do not correspond to any board tile.");
        return board[row][col];
    }

    // Returns the board's side size
    public int size() {
        return sideSize;
    }

    // Returns the number of tiles out of te correct position
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

    // Returns the sum of manhattan distances of all tile to their position in
    // the solved state.
    public int manhattan() {
        if (manhattan_cache == -1) {
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
        else
            return manhattan_cache;
    }

    // Returns if the board is in the solved state
    public boolean isGoal() {
        return manhattan() == 0 && hamming() == 0;
    }

    // Returns if the board can be solved by a sequence of legal moves
    public boolean isSolvable() {
        int pos, pos2, inversions = 0, blankRow = 0;
        for (int row = 0; row < sideSize; row++) {
            for (int col = 0; col < sideSize; col++) {
                pos = ((row * sideSize) + col);
                if (board[row][col] == 0) blankRow = row;
                for (int row2 = row; row2 < sideSize; row2++) {
                    for (int col2 = col; col2 < sideSize; col2++) {
                        pos2 = ((row2 * sideSize) + col2);
                        if (board[row][col] < board[row2][col2] && pos > pos2)
                            inversions++;
                    }
                }
            }
        }
        if (sideSize % 2 == 0 && (inversions + blankRow) % 2 == 0)
            return false;
        if (sideSize % 2 != 0 && inversions % 2 != 0)
            return false;
        return true;
    }

    // Returns if the board equals the given object
    // The board is equal to the object if the object is a board and its tiles
    // are at the same position as the board.
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
                return new BoardIterator();
            }
        };
    }
    private class BoardIterator implements Iterator<Board> {
        int zeroCol, zeroRow, count;
        LinkedStack<Board> ls;
        int[][] tmp = new int[sideSize][sideSize];
        BoardIterator() {
            for (int row = 0; row < sideSize; row++) {
                for (int col = 0; col < sideSize; col++) {
                    if (board[row][col] == 0) {
                        zeroCol = col;
                        zeroRow = row;
                    }
                }
            }
            ls = new LinkedStack<Board>();
            for(int i = 0; i < sideSize ; i++) {
                for(int j = 0; j<sideSize ; j++) {
                    tmp[i][j] = tileAt(i,j);
                }
            }
            //TOP
            if (zeroRow - 1 >= 0) {
                tmp[zeroRow][zeroCol] = tmp[zeroRow - 1][zeroCol];
                tmp[zeroRow - 1][zeroCol] = 0;
                Board top = new Board(tmp);
                ls.push(top);
                tmp[zeroRow][zeroCol] = tileAt(zeroCol,zeroRow);
                tmp[zeroRow - 1][zeroCol] = tileAt(zeroRow - 1, zeroCol);
            }
            //RIGHT
            if (zeroCol + 1 < sideSize) {
                tmp[zeroRow][zeroCol] = tmp[zeroRow][zeroCol + 1];
                tmp[zeroRow][zeroCol + 1] = 0;
                Board right = new Board(tmp);
                ls.push(right);
                tmp[zeroRow][zeroCol] = tileAt(zeroCol,zeroRow);
                tmp[zeroRow][zeroCol + 1] = tileAt(zeroRow, zeroCol + 1);
            }
            //BOTTOM
            if (zeroRow + 1 < sideSize) {
                tmp[zeroRow][zeroCol] = tmp[zeroRow + 1][zeroCol];
                tmp[zeroRow + 1][zeroCol] = 0;
                Board right = new Board(tmp);
                ls.push(right);
                tmp[zeroRow][zeroCol] = tileAt(zeroCol,zeroRow);
                tmp[zeroRow + 1][zeroCol] = tileAt(zeroRow + 1, zeroCol);
            }
            //LEFT
            if (zeroCol - 1 < sideSize) {
                tmp[zeroRow][zeroCol] = tmp[zeroRow][zeroCol - 1];
                tmp[zeroRow][zeroCol - 1] = 0;
                Board right = new Board(tmp);
                ls.push(right);
                tmp[zeroRow][zeroCol] = tileAt(zeroCol,zeroRow);
                tmp[zeroRow][zeroCol - 1] = tileAt(zeroRow, zeroCol - 1);
            }
        }
        public boolean hasNext() {
            return !ls.isEmpty();
        }
        public Board next() {
            return ls.pop();
        }
        public void remove(){
        }
    }

    // Returns a textual representation of the board
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

    // Unit test
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
