import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class follow_the_path {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());
        int row = 0;
        int col = Integer.parseInt(tokens.nextToken()) - 1;

        char[][] instructions = new char[N][M];
        int[][] visited = new int[N][M];
        String line;
        for (int i = 0; i < N; i++) {
            line = read.readLine();
            for (int j = 0; j < M; j++) {
                instructions[i][j] = line.charAt(j);
                visited[i][j] = -1;
            }
        }
        // everything's initialized
        int moves = 0;
        while (row >= 0 && row < N && col >= 0 && col < M) {
            char in = instructions[row][col];
            if (visited[row][col] != -1) {
                System.out.println(visited[row][col] + " step(s) before a loop of " + (moves - visited[row][col]) + " step(s)");
                return;
            }
            visited[row][col] = moves;
            if (in == 'E') {
                col++;
            } else if (in == 'W') {
                col--;
            } else if (in == 'N') {
                row--;
            } else { // char is 'S'
                row++;
            }
            moves++;
        }
        System.out.println(moves + " step(s) to exit");
    }
}
