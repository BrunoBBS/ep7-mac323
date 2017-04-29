import edu.princeton.cs.algs4.*;


public class Solver {

    MinPQ<BoardWrapper> pq;
    private int steps_until_now;

    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException("Initial board must be not null.");
        if (initial.isSolvable() == false) throw new  IllegalArgumentException("Initial board is not solvable.");
        steps_until_now = 0;
        pq = new MinPQ<BoardWrapper>();
        BoardWrapper initialw = new BoardWrapper(steps_until_now, initial);
        BoardWrapper curr = initialw;
        while (!curr.board.isGoal()) {
            for (Board b : curr.board.neighbors()) {
                //critical optimization
                if (!b.equals(curr.board))
                    pq.insert(new BoardWrapper(steps_until_now + 1, b));
            }
            steps_until_now++;
        }
        StdOut.println("CABOOOOOU " + curr.board);
    }

    public int moves() {
        return 2;
    }
    public Iterable<Board> solution() {
        return null;
    }
    public static void main(String[] args) {
    }

    private class BoardWrapper implements Comparable<BoardWrapper> {
        private int steps;
        public Board board;
        public BoardWrapper(int s, Board b) {
            board = b;
            steps = s;
        }
        private int priority() {
            return this.board.manhattan() + this.steps;
        }
        public int compareTo(BoardWrapper bw) {
            return (this.priority()) - (bw.priority());
        }
    }
}
