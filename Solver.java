import edu.princeton.cs.algs4.*;


public class Solver {
    MinPQ pq;
    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException("Initial board must be not null.");
        if (initial.isSolvable() == false) throw new  IllegalArgumentException("Initial board is not solvable.");
        pq = new MinPQ();
    }
    public int moves() {

    }
    public Iterable<Board> solution() {

    }
    public static void main(String[] args) {

    }
}
