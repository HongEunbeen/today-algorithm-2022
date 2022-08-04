import java.io.*;
import java.util.*;

class Edge{
    int index;
    int time;

    Edge(int index, int time){
        this.index = index;
        this.time = time;
    }
}

public class Main {
    static int N, M, X;
    static List<Edge>[] list;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 마을 갯수 ( = 학생 수)
        M = Integer.parseInt(st.nextToken()); // 마을 사이 도로 갯수
        X = Integer.parseInt(st.nextToken()); // 목적지

        list = new ArrayList[N+1];
        dp = new int[N+1];

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
            dp[i] = 9999;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            
            list[start].add(new Edge(end, time));
        }

        Dijkstra(X);

        for(int i=1; i<=N; i++){
            dp[i] += DijkstraTo(i);
        }

        Arrays.sort(dp);
        System.out.println(dp[N]);

    }
    static void Dijkstra(int index){
        Queue<Edge> queue = new LinkedList<>();
        dp[index] = 0; // 최소 비용

        queue.add(new Edge(index, 0));

        while(!queue.isEmpty()){
            Edge edge = queue.poll();
            int current = edge.index;
            int distance = edge.time;

            if(dp[current] < distance) continue;
            
            Iterator iter = list[current].iterator();
            while(iter.hasNext()){
                Edge next = (Edge) iter.next();

                int nextI = next.index; 
                int nextD = distance + next.time;

                if(nextD < dp[nextI]) {
                    dp[nextI] = nextD;
                    queue.add(new Edge(nextI, nextD));
                }
            }
        }
    }

    static int DijkstraTo(int index){
        Queue<Edge> queue = new LinkedList<>();
        int dpTo[] = new int[N+1];

        for(int i=1; i<=N; i++){
            dpTo[i] = 9999;
        }

        dpTo[index] = 0;
        queue.add(new Edge(index, 0));

        while(!queue.isEmpty()){
            Edge edge = queue.poll();
            int currentI = edge.index;
            int currentD = edge.time;

            if(dpTo[currentI] < currentD) continue;
            
            Iterator iter = list[currentI].iterator();
            while(iter.hasNext()){
                Edge next = (Edge) iter.next();

                int nextI = next.index; 
                int nextD = currentD + next.time;

                if(nextD < dpTo[nextI]) {
                    dpTo[nextI] = nextD;
                    if(nextI == X) return dpTo[nextI];
                    queue.add(new Edge(nextI, nextD));
                }
            }
        }
        return 0;
    }
}

