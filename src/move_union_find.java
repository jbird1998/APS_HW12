import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class move_union_find {

    static int[] sizes;
    static long[] sets;

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(read.readLine());
        StringBuilder output = new StringBuilder(100000);
        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        sets = new long[100001]; // the sum of each set, negated.
        sizes = new int[100001]; // the cardinality of each set.
        for (int i = 1; i <= 100000; i++) {
            sets[i] = -1 * i;
            sizes[i] = 1;
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(read.readLine());
            int op = Integer.parseInt(tokens.nextToken());
            int a = Integer.parseInt(tokens.nextToken());
            int b = 1;
            if (tokens.hasMoreTokens()) {
                b = Integer.parseInt(tokens.nextToken());
            }
            if (op == 1) {
                union(a, b);
            } else if (op == 2) {
                int i1 = find(a);
                int i2 = find(b);
                if (i1 == i2) {
                    continue;
                }
                if (i1 == a) { // we have to move the root
                    int elements = sizes[i1] - 1;
                    int replacement = -1;
                    for (int j = 1; j <= N; j++) {
                        if (elements == 0) {
                            break;
                        }
                        if (sets[j] == i1) {
                            if (replacement == -1) { // this will be our root replacement
                                replacement = j;
                                sizes[j] = sizes[i1] - 1;
                                sets[j] = sets[i1] + i1;
                            } else {
                                sets[j] = replacement;
                            }
                            elements--;
                        }
                    }
                } else {
                    sets[i1] += a;
                    sizes[i1]--;
                }
                sets[a] = i2;
                sets[i2] -= a;
                sizes[i2]++;
            } else {
                int index = find(a);
                output.append(sizes[index]).append(" ").append(-1 * sets[index]).append("\n");
            }
//            StringBuilder o1 = new StringBuilder();
//            StringBuilder o2 = new StringBuilder();
//            for (int j = 1; j <= N; j++) {
//                o1.append(sets[j]);
//                o1.append(' ');
//                o2.append(sizes[j]);
//                o2.append(' ');
//            }
//            System.out.println("Sets: " + o1.toString());
//            System.out.println("\tSizes:" + o2.toString());
        }
        System.out.print(output.toString());
    }

    public static int find(int i) {
        // Returns the index
        if (sets[i] < 0L) {
            return i;
        }
        sets[i] = find((int)sets[i]);
        return (int)sets[i];
    }

    public static void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);
        if (aP == bP) {
            return;
        }
        if (sizes[aP] < sizes[bP]) {
            sets[bP] += sets[aP];
            sets[aP] = bP;
            sizes[bP] += sizes[aP];
        } else {
            sets[aP] += sets[bP];
            sets[bP] = aP;
            sizes[aP] += sizes[bP];
        }
    }
}
