import edu.princeton.cs.algs4.*;


public class Solver {

    MinPQ<BoardWrapper> pq;
    Queue<BoardWrapper> plays;
    private int steps_until_now;

    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException("Initial board must be not null.");
        if (initial.isSolvable() == false) throw new  IllegalArgumentException("Initial board is not solvable.");
        steps_until_now = 0;
        plays = new Queue<BoardWrapper>();
        pq = new MinPQ<BoardWrapper>();
        boolean different;
        BoardWrapper initialW = new BoardWrapper(steps_until_now, initial);
        BoardWrapper curr = initialW;
        while (!curr.board.isGoal()) {
            // Puts the possible plays in the pq
            for (Board b : curr.board.neighbors()) {
                //critical optimization
                if (!b.equals(curr.board))
                    pq.insert(new BoardWrapper(steps_until_now + 1, b));
            }
            different = true;
            for (BoardWrapper b2 = pq.delMin(); different; b2 = pq.delMin()) {
                for (BoardWrapper played : plays.iterator()) {
                    if (b2.board.equals(played.board)) {
                        different = false;
                        break;
                    }
                }
            }
        }
        StdOut.println("CABOOOOOU \n" + curr.board);
    }

    public int moves() {
        return steps_until_now;
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
