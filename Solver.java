import edu.princeton.cs.algs4.*;
import java.lang.*;
import java.util.Iterator;


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
        boolean contains;
        BoardWrapper initialW = new BoardWrapper(steps_until_now, initial);
        BoardWrapper curr = initialW;
        StdOut.println(curr.board);
        while (!curr.board.isGoal()) {
            // Puts the possible plays in the pq
            StdOut.println("Not the goal yet");
            for (Board b : curr.board.neighbors()) {
                //critical optimization
                if (!b.equals(curr.board)) {
                    pq.insert(new BoardWrapper(steps_until_now + 1, b));
                }
            }
            StdOut.println("I am the best board:\n " + pq.min().board );
            // Uses only boards that have not appeared before
            contains = true;
            BoardWrapper b2 = pq.delMin();
            while (contains == true) {
                contains = false;
                // Uses only boards that have not appeared before
                for (BoardWrapper played : plays) {
                    if (b2.board.equals(played.board)) {
                        contains = true;
                        break;
                    }
                }
                if (contains)
                    b2 = pq.delMin();
            }
            curr = b2;
            plays.enqueue(curr);
            StdOut.println("I am the best neighbor \n " + curr.board);
        }
        StdOut.println("CABOOOOOU \n" + curr.board);
    }

    public int moves() {
        return steps_until_now;
    }
    public Iterable<Board> solution() {
        return new Iterable<Board> () {
            public Iterator<Board> iterator() {
                return new SolutionIterable();
            }
        };
    }

    private class SolutionIterable implements Iterator<Board> {
       public boolean hasNext() {
           return !plays.isEmpty();
       }
       public Board next() {
           return plays.dequeue().board;
       }
       public void remove() {
       }
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
