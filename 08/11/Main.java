import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int end;
    int weight;

    Edge(int end, int weight){
        this.end = end;
        this.weight= weight;
    }

    @Override
    public int compareTo(Edge e) {
        return weight- e.weight;
    }
}

public class Main {
    static List<Edge>[] list;
    static boolean[] checked;
    static int[] distance;
    static int V, start, end, weight;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());

        checked = new boolean[V+1];
        distance = new int[V+1];
        list = new ArrayList[V+1];

        for(int i=1; i<=V; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=1; i<=V; i++){
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                end = Integer.parseInt(st.nextToken());
                if(end == -1) continue;
                weight = Integer.parseInt(st.nextToken());
                list[start].add(new Edge(end, weight));
            }
        }

        dijkstra(1);
        int max = 0, maxI = 0;
        for(int i=1; i<=V; i++){
            if(distance[i] > max){
                max = distance[i];
                maxI = i;
            }
        }

        dijkstra(maxI);
        Arrays.sort(distance);
        System.out.println(distance[V-1]);

    }
    public static void dijkstra(int index) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(checked, false);

        distance[index] = 0;
        queue.add(new Edge(index, 0));

        while(!queue.isEmpty()){
            Edge edge = queue.poll();

            int currentI = edge.end;
            int currentW = edge.weight;

            if(checked[currentI]) continue;
            checked[currentI] = true;

            Iterator iter = list[currentI].iterator();
            while(iter.hasNext()){
                Edge next = (Edge) iter.next();

                int nextI = next.end;
                int nextW = currentW + next.weight;

                if(nextW < distance[nextI]) {
                    distance[nextI] = nextW;
                    queue.add(new Edge(nextI, nextW));
                }
            }
        }
    }
}
