import java.io.*;
import java.util.*;

public class Main {
    static int M, N, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //땅의 세로
        M = Integer.parseInt(st.nextToken()); //땅의 가로
        B = Integer.parseInt(st.nextToken()); //인벤토리 기본 블록 개수


        int max = 0, min = Integer.MAX_VALUE;

        int[][] block = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                block[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(block[i][j], min);
                max = Math.max(block[i][j], max);
            }
        }

        int time = Integer.MAX_VALUE; // 시간
        int height = -1; // 층
        
        //B가 0이라면 무조건 빼야함
        for (int i = min; i <= max; i++) {
            int cnt = 0;
            int inventory = B;

            for (int j=0; j<N; j++) {
                for (int z=0; z<M; z++) {
                    int num = block[j][z]-i;
                    if(num > 0) {
                        inventory += Math.abs(num);
                        cnt += Math.abs(num) * 2;
                    }
                    else if(num < 0){
                        inventory += Math.abs(num) * -1;
                        cnt += Math.abs(num);
                    }
                }
            }

            if(inventory >= 0 && cnt <= time) {
                time = cnt;
                height = i;
            }
        }

        System.out.println(time + " " + height);
    }
}
