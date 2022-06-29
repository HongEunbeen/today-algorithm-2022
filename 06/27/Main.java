import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i]=Integer.parseInt(br.readLine());
        }

        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int[] dp = new int[N + 2];
        for (int i = 1; i <= N + 1; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            for (int j = 1; j <= M && i + 2 * j <= N + 1; j++) {
                dp[i + 2 * j] = Math.max(dp[i + 2 * j], dp[i] + sum[i + j - 1] - sum[i - 1]);
            }
        }

        System.out.println(dp[N + 1]);
    }
}
