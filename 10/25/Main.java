import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N, M, max;
    static int[][] farm;
    static int[][] dp;
    static List<Integer> seed;
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //농장의 크기
        M = Integer.parseInt(st.nextToken()); //씨앗을 뿌린 횟수
      
        dp = new int[N][N];
        farm = new int[N][N];
        seed = new ArrayList<>();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int l =Integer.parseInt(st.nextToken());
            int f =Integer.parseInt(st.nextToken());

            for(int i=x; i<x+l; i++){
                for(int j=y; j<y+l; j++){
                    farm[i][j] = f;
                }
            }

            seed.add(f);
        }
        if(seed.size() <= 1){
            fill(seed.get(0));
            getDP();
        }
        else{
            for(int i=0; i<seed.size()-1; i++){
                DFS(1, i, i+1);
            }
        }

        System.out.println(max*max);
    }
    public static void DFS(int count, int standard, int idx){
        if(count == 2){
            getDP();
            return;
        }

        fill(seed.get(standard));
        fill(seed.get(idx));
        DFS(count+1, standard, idx+1);

        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], 0);
        }
    }
    public static void fill(int num){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(farm[i][j] == num){
                    dp[i][j] = 1;
                }
            }
        }
    }
    public static int getDP(){
        for(int i=1; i<N; i++){
            for(int j=1; j<N; j++){
                if(dp[i][j] <= 0) continue;

                dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
