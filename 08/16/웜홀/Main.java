import java.io.*;
import java.util.*;

class Edge {
    int end;
    int time;

    Edge(int end, int time){
        this.end = end;
        this.time = time;
    }
}

public class Main {
    public static int TC ,N, M, W;
    public static ArrayList<Edge> list[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        while(TC-- > 0){
            st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken()); //지점의 수
            M = Integer.parseInt(st.nextToken()); //도로의 수
            W = Integer.parseInt(st.nextToken()); //웜홀의 수

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

            System.out.println(bellman() ? "YES" : "NO");
        }

    }
    public static boolean bellman(){
        int[] distance = new int[N+1];
        boolean update = false;
        for(int i=1; i<=N; i++){
            update =false;
            for(int cur=1; cur<=N; cur++){
                for (Edge edge : list[cur]) {
                    if(distance[edge.end] > distance[cur] + edge.time){
                        distance[edge.end] = distance[cur] + edge.time;
                        update = true;
                    }
                }
            }
            if(!update)
                break;
        }
        return update;
    }
}
