import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] tree;
	static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N];
        tree = new ArrayList[N];
        for(int i=0; i<N; i++){
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        Integer.parseInt(st.nextToken());

        for(int i=1; i<N; i++){
            int dept = Integer.parseInt(st.nextToken());
            tree[dept].add(i);
        }
        
        System.out.println(DFS(0));

    }
    public static int DFS(int index) {
        Iterator iter = tree[index].iterator();

        while(iter.hasNext()){
            int next = (int)iter.next();
			dp[next] = DFS(next) + 1;
        }

        Collections.sort(tree[index], (o1, o2) -> dp[o2]-dp[o1]);

		int max = 0, i = 0;
        iter = tree[index].iterator();

        while(iter.hasNext()){
            int num = (int)iter.next();
			dp[num] += i++;
			max = Math.max(max, dp[num]);
        }

		return max;
    }
}
