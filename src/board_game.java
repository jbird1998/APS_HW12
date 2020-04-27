import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class board_game {
    static int[] rows;
    static int[][] board;
    static int N, sum = 0, min = Integer.MAX_VALUE;

    private static boolean place(int col, int row) {
        for (int prev = 0; prev < col; prev++) {
            if (rows[prev] == row) {
                return false;
            }
        }
        return true;
    }
    private static void backtrack(int col) {
        int toAdd = 0;
        for (int row = 0; row < N; row++) {
            if (place(col, row)) {
                rows[col] = row;
                toAdd = board[row][col];
                sum += toAdd;
                if (col + 1 == N) { // solution
                    // System.out.println("\t" + sum);
                    if (sum < min) {
                        min = sum;
                    }
                } else {
                    backtrack(col + 1);
                }
                sum -= toAdd;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(read.readLine());
        rows = new int[N];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            rows[i] = -1;
            StringTokenizer tokens = new StringTokenizer(read.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        backtrack(0);
        System.out.println(min);
    }
}
