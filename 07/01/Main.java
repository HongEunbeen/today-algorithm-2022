import java.io.*;
import java.util.*;


public class Main {
    final static int MAX = 1000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());

        // 전체 일 수, 지각 횟수, 결석 횟수(연속)
        int dp[][][] = new int[N+1][4][5];

        if(N == 0){
            System.out.println(0);
            System.exit(0);
        }
        dp[0][0][0] = 1;
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        dp[1][1][0] = 1;

        for(int i=2; i<=N; i++){
            for(int j=0; j<2; j++){
              for(int z=0; z<3; z++){
                  if(z == 0){
                      dp[i][j][z] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % MAX;
                      if(j == 1){
                          dp[i][j][z] += (dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % MAX;;
                      }
                  }
                  else dp[i][j][z] = (dp[i-1][j][z-1]) % MAX;
              }
            }
        }

        int cnt = 0;
        for(int i=0; i<2; i++){
            for(int j=0; j<3; j++){
                cnt += dp[N][i][j] % MAX;
            }
        }

        System.out.println(cnt%MAX);

        //O 로 시작 =
        //f(a,b) = f(a-1,b) + f(a,b-1) (a,b >= 1 )
        //dp[전체 일수][지각 횟수][결석 횟수] = dp[일][][]
        // L < 2 || A < 3
    }
}
