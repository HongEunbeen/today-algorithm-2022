import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int end;
    int time;

    Edge(int end, int time){
        this.end = end;
        this.time = time;
    }

    @Override
    public int compareTo(Edge e) {
      return time- e.time;
    }
}

public class Main {
    public static int INF = 50000;
    public static int TC ,N, M, W;
    public static ArrayList<Edge> list[];
    public static int[] distance;
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
       
        while(TC-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            
            N = Integer.parseInt(st.nextToken()); //지점의 수
            M = Integer.parseInt(st.nextToken()); //도로의 수
            W = Integer.parseInt(st.nextToken()); //웜홀의 수

            distance = new int[N+1];
            list = new ArrayList[N+1];
    
            for(int i=1; i<=N; i++){
                list[i] = new ArrayList<>();
            }

            for(int i=1; i<=M; i++){
                st = new StringTokenizer(br.readLine(), " ");
                
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
    
                list[start].add(new Edge(end, time));
                list[end].add(new Edge(start, time));
            }
            
            for(int i=1; i<=W; i++){
                st = new StringTokenizer(br.readLine(), " ");
                
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
    
                list[start].add(new Edge(end, -time));
            }
            
            if(bellman(1)) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        
        System.out.println(sb);
    }
    public static boolean bellman(int start){
        Arrays.fill(distance, INF);
        
        distance[start] = 0;
        for(int i=1; i<=N; i++) {
            Iterator iter = list[i].iterator();
            while(iter.hasNext()){
                Edge edge = (Edge) iter.next();

                int nextI = edge.end;
                int nextT = edge.time;

                if (distance[i] == INF) continue;
				if (distance[nextI] > distance[i] + nextT) {
					if (i == N) {
						return true;
					}
					distance[nextI] = distance[i] + nextT;
				}
            }
		}
        return false;
    }
}

