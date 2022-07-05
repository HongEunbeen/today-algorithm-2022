import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static List<Integer>[] list;
    static int[] cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cnt = new int[N+1];
        list = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        // 직속상사 관계 설정
        st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i <= N; i++) {
            int item = Integer.parseInt(st.nextToken());
            if (i != 1) list[item].add(i);
        }

        // 칭찬 리스트
        for (int i=0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cnt[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }

        DFS(1);

        for (int i=1; i <= N; i++) {
            System.out.print(cnt[i] + " ");
        }
    }
    public static void DFS(int index) {
        Iterator iter = list[index].iterator();

        while(iter.hasNext()){
            int next = (int)iter.next();
            cnt[next] += cnt[index];
            DFS(next);
        }
    }
}
