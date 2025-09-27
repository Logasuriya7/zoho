import java.util.*;

public class Main {

    public static void main(String[] args) {
        SnakeGame snackgame = new SnakeGame(6, 6);
        snackgame.startgame();
    }
}

class Node {
    int row;
    int cal;

    Node(int row, int cal) {
        this.row = row;
        this.cal = cal;
    }
}

class SnakeGame {

    private final String[][] board;
    private final Queue<Node> snakebody = new LinkedList<>();
    private final Queue<Node> foodqueue = new LinkedList<>();
    private final Random random = new Random();
    private int currentrow = 0;
    private int currentcol = 0;
    private String currenthead = ">";

    SnakeGame(int row, int col) {
        board = new String[row][col];
        snakebody.add(new Node(0, 0));
        board[0][0] = currenthead;
        Generatefoodpossition();
        allocatefood();
    }

    private void Generatefoodpossition() {
        foodqueue.add(new Node(1, 0));
        foodqueue.add(new Node(2, 2));
        foodqueue.add(new Node(3, 4));
        foodqueue.add(new Node(5, 2));
        foodqueue.add(new Node(4, 5));
    }

    private void allocatefood() {
        Node food;
        if (!foodqueue.isEmpty()) {
            food = foodqueue.poll();
        } else {
            do {
                food = new Node(random.nextInt(board.length), random.nextInt(board[0].length));
            } while (board[food.row][food.cal] != null);
        }
        board[food.row][food.cal] = "X";
    }

    public void startgame() {
        Scanner sc = new Scanner(System.in);
        printboard();
        while (true) {
            System.out.print("direction want to go -- U, D, R, L: ");
            char direction = sc.next().toUpperCase().charAt(0);
            if (makemove(direction)) {
                printboard();
            } else {
                System.out.println("Game Over");
                break;
            }
        }
        sc.close();
    }

    private boolean makemove(char direction) {
        int newcol = currentcol;
        int newrow = currentrow;
        String newhead;
        switch (direction) {
            case 'U': newrow--; newhead = "∧"; break;
            case 'D': newrow++; newhead = "∨"; break;
            case 'R': newcol++; newhead = ">"; break;
            case 'L': newcol--; newhead = "<"; break;
            default: System.out.println("Invalid Direction -- use U, D, R, L"); return true;
        }

        if (!isValidMove(newrow, newcol)) return false;

        boolean ateFood = "X".equals(board[newrow][newcol]);

        if (!ateFood) {
            Node tail = snakebody.poll();
            board[tail.row][tail.cal] = null;
        } else {
            allocatefood();
        }

        // Update snake body and head position
        snakebody.add(new Node(newrow, newcol));
        currentrow = newrow;
        currentcol = newcol;
        currenthead = newhead;
        board[newrow][newcol] = newhead;

        return true;
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        // Allow empty or food
        if (board[row][col] == null || "X".equals(board[row][col])) return true;

        // Allow moving into the tail (it will move)
        Node tail = snakebody.peek();
        if (tail != null && tail.row == row && tail.cal == col) return true;

        return false;
    }

    private void printboard() {
        for (String[] row : board) {
            for (String element : row) {
                System.out.print((element == null ? "■" : element) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
