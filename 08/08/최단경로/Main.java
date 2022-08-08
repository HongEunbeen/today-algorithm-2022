import java.io.*;
import java.util.*;

class Edge{
    int index;
    int weight;

    Edge(int index, int weight){
        this.index = index;
        this.weight = weight;
    }
}

public class Main {
    static int V, E, K;
    static List<Edge>[] list;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken()); // 정점 개수
        E = Integer.parseInt(st.nextToken()); // 간선 개수

        list = new ArrayList[V+1];
        dp = new int[V+1];

        for(int i=1; i<=V; i++){
            list[i] = new ArrayList<>();
            dp[i] = Integer.MAX_VALUE;
        }

        K = Integer.parseInt(br.readLine()); // 시작 정점

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            list[start].add(new Edge(end, weight));
        }

        Dijkstra(K);
        Arrays.sort(dp);

        for(int i=1; i<=V; i++){
            if(dp[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dp[i]);
        }

    }
    static void Dijkstra(int index){
        Queue<Edge> queue = new LinkedList<>();
        dp[index] = 0; // 최소 비용

        queue.add(new Edge(index, 0));

        while(!queue.isEmpty()){
            Edge edge = queue.poll();
            int current = edge.index;
            int weight = edge.weight;

            if(dp[current] < weight) continue;
            
            Iterator iter = list[current].iterator();
            while(iter.hasNext()){
                Edge next = (Edge) iter.next();

                int nextI = next.index; 
                int nextW = weight + next.weight;

                if(nextW < dp[nextI]) {
                    dp[nextI] = nextW;
                    queue.add(new Edge(nextI, nextW));
                }
            }
        }
    }
}
