import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int index;
    int time;

    Edge(int index, int time){
        this.index = index;
        this.time = time;
    }

    @Override
    public int compareTo(Edge e) {
        return time - e.time;
    }
}

public class Main {
    static int N, M, X;
    static List<Edge>[] list, rlist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 마을 갯수 ( = 학생 수)
        M = Integer.parseInt(st.nextToken()); // 마을 사이 도로 갯수
        X = Integer.parseInt(st.nextToken()); // 목적지

        list = new ArrayList[N+1];
        rlist = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
            rlist[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            
            list[start].add(new Edge(end, time));
            rlist[end].add(new Edge(start, time));
        }

        int dist1[] = Dijkstra(X, list);
        int dist2[] = Dijkstra(X, rlist);

        int cnt = 0;
        for(int i=1; i<=N; i++){
            cnt = Math.max(cnt, dist1[i] + dist2[i]);
        }
        System.out.println(cnt);

    }
    static int[] Dijkstra(int index, List<Edge>[] list){
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        int[] dp = new int[N+1];
        
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[index] = 0;
        queue.add(new Edge(index, 0));

        while(!queue.isEmpty()){
            Edge edge = queue.poll();
            int current = edge.index;
            int time = edge.time;

            if(dp[current] < time) continue;

            Iterator iter = list[current].iterator();
            while(iter.hasNext()){
                Edge next = (Edge) iter.next();

                int nextI = next.index; 
                int nextD = time + next.time;

                if(nextD < dp[nextI]) {
                    dp[nextI] = nextD;
                    queue.add(new Edge(nextI, nextD));
                }
            }
        }
        return dp;
    }
}
