import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int index;
    int distinct;

    Edge(int index, int distinct){
        this.index = index;
        this.distinct = distinct;
    }

    @Override
    public int compareTo(Edge e) {
        return distinct - e.distinct;
    }
 
}

public class Main {
    static int N, E, SUM;
    static List<Edge>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 정점 개수
        E = Integer.parseInt(st.nextToken()); // 간선 개수

        list = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distinct = Integer.parseInt(st.nextToken());
            
            list[a].add(new Edge(b, distinct));
            list[b].add(new Edge(a, distinct));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int v1 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점 1
        int v2 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점 2

        int start1[] = Dijkstra(1);

        int sum1 = start1[v1]; // 1-> v1
        int sum2 = start1[v2]; // 1-> v2

        int start2[] = Dijkstra(v1);
        sum1 += start2[v2]; // v1 -> v2
        sum2 += start2[N]; // v1 -> N

        int start3[] = Dijkstra(v2);
        sum1 += start3[N];// v2 -> N
        sum2 += start3[v1];// v2 -> v1

        System.out.println(Math.min(sum1, sum2) == Integer.MAX_VALUE ? -1 : Math.min(sum1, sum2));
    }

    static int[] Dijkstra(int index){
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[index] = 0;
        queue.add(new Edge(index, 0));

        while(!queue.isEmpty()){
            Edge edge = queue.poll();

            int current = edge.index;
            int distinct = edge.distinct;

            if(dp[current] < distinct) continue;
            
            Iterator iter = list[current].iterator();
            while(iter.hasNext()){
                Edge next = (Edge) iter.next();

                int nextI = next.index; 
                int nextD = distinct + next.distinct;

                if(nextD < dp[nextI]) {
                    dp[nextI] = nextD;
                    queue.add(new Edge(nextI, nextD));
                }
            }
        }

        return dp;
    }
}
