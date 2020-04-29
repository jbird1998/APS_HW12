import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class yasp {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        ArrayList<Integer> queue = new ArrayList<>(30);
        while (tokens.hasMoreTokens()) {
            queue.add(Integer.parseInt(tokens.nextToken()));
        }
        // NOTE: First element is top of stack
        int N = queue.size();
        if (N <= 1) {
            System.out.println("0");
            return;
        }
        int bound = N - 1;
        StringBuilder output = new StringBuilder(1000);
        while (bound > 0) {
            int max = 0;
            int val = queue.get(0);
            for (int i = 1; i <= bound; i++) {
                int curr = queue.get(i);
                if (curr > val) {
                    val = curr;
                    max = i;
                }
            }
            if (max != bound) {
                if (max != 0) {
                    ArrayList<Integer> replace = new ArrayList<>(30);
                    for (int i = max; i >= 0; i--) {
                        replace.add(queue.get(i));
                    }
                    for (int i = max + 1; i < N; i++) {
                        replace.add(queue.get(i));
                    }
                    output.append(N - max).append(" ");
                    queue = replace;
                }
                // now max is guaranteed to be the first element
                ArrayList<Integer> replace = new ArrayList<>(30);
                for (int i = bound; i >= 0; i--) {
                    replace.add(queue.get(i));
                }
                for (int i = bound + 1; i < N; i++) {
                    replace.add(queue.get(i));
                }
                output.append(N - bound).append(" ");
                queue = replace;
            }
            bound--;
            // System.out.println("\t" + queue.toString());
        }
        output.append("0");
        System.out.println(output.toString());
        // System.out.println("\t" + queue.toString());
    }
}
