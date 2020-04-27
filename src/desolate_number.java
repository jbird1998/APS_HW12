import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class desolate_number {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        int A = Integer.parseInt(tokens.nextToken());
        int B = Integer.parseInt(tokens.nextToken());
        int N = A + B;
        long result = 0;
        int numA = 0;
        int[] parts = new int[N];

        int index = N - 1;
        while (A - numA > 2*B) {
            if (parts[index] == 0) {
                parts[index] = 1;
                numA++;
            }
            index--;
        }
        int offset = index;
        for (int i = index; i >= 0; i--) {
            if (((offset) - i) % 3 == 0 || ((offset) - i) % 3 == 2) {
                parts[i] = 1;
                numA++;
            } else {
                parts[i] = 0;
            }
        }
        index = 0;
        while (numA > A) { // too many 1s
            if (parts[index] == 1) {
                parts[index] = 0;
                numA--;
            }
            index++;
        }
        for (int i = 0; i < N; i++) {
            // System.out.println("\t" + parts[i]);
            result += parts[i] * Math.pow(2, N - 1 - i);
        }
        System.out.println(result);
    }
}
