import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int dp[][] = new int[3][2];
        int x, y, z;
        int first = 0, mid = 0, end = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine()," ");

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            if(i == 0){
                dp[0][0] = dp[0][1] = x;
                dp[1][0] = dp[1][1] = y;
                dp[2][0] = dp[2][1] = z;
            }
            else{
                first = dp[0][0];
                mid = dp[1][0];
                end = dp[2][0];

                dp[0][0] = Math.min(first, mid) + x;
                dp[1][0] = Math.min(Math.min(first, mid), end) + y;
                dp[2][0] = Math.min(mid, end) + z;

                first = dp[0][1];
                mid = dp[1][1];
                end = dp[2][1];

                dp[0][1] = Math.max(first, mid) + x;
                dp[1][1] = Math.max(Math.max(first, mid), end) + y;
                dp[2][1] = Math.max(mid, end) + z;
            }
        }

        int min = Math.min(Math.min(dp[0][0], dp[1][0]), dp[2][0]);
        int max = Math.max(Math.max(dp[0][1], dp[1][1]), dp[2][1]);

        System.out.println(max + " " + min);
    }
}

