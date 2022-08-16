import java.io.*;
import java.util.*;

class Edge {
    int end;
    int weight;

    Edge(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
      return weight- e.weight;
    }
}

public class Main {
    public static int N, M;
    public static ArrayList<Edge> list[];
    public static int[] distance;
    public static boolean[] checked;
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        distance = new int[N+1];
        checked = new boolean[N+1];
        list = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList();
        }

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Edge(end, weight));
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int startV = Integer.parseInt(st.nextToken());
        int endV = Integer.parseInt(st.nextToken());

        dijkstra(startV);

        System.out.println(distance[endV]);
    }
    public static void dijkstra(int start){
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
        Arrays.fill(distance, 100000);
        Arrays.fill(checked, false);
        
        distance[start] = 0;
        queue.add(new Edge(start, 0));
        
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

