import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int max = 0;

        N = Integer.parseInt(st.nextToken()); //나무의 수 N
        M = Integer.parseInt(st.nextToken()); //필요한 M미터

        int[] tree = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
            if(max < tree[i]) max = tree[i];
        }

        int left = 0;
        int right = max;

        while (left <= right) {
            int mid = (left + right) / 2;
            long cnt = 0;
            for(int j=0; j<N; j++){
                cnt += Math.max(tree[j] - mid, 0);
            }
            if(cnt < M)
                right = mid - 1;
            else{
                left = mid + 1;
            }
        }

        System.out.println(left-1);
    }
}
