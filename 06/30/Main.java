import java.io.*;
import java.util.*;


public class Main {
    final static int INF = 99999;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int V = Integer.parseInt(st.nextToken());

        int dist[][] = new int[V][V];
        int graph[][] = new int[V][V];

        int i, j, k;

        for (i = 0; i < V; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for (j = 0; j < V; j++){
                graph[i][j] = dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (k = 0; k < V; k++)
        {
            for (i = 0; i < V; i++)
            {
                for (j = 0; j < V; j++)
                {
                    if (i == j || i == k || k == j) {
                        continue;
                    }
                    if (dist[i][k] + dist[k][j] < dist[i][j]){
                        System.out.println("-1");
                        System.exit(0);
                    }

                    if (dist[i][j] == dist[i][k] + dist[k][j])
                        graph[i][j] = INF;

                }
            }
        }

        int ans = 0;
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if (graph[i][j] != INF && i != j) {
                    ans += graph[i][j];
                }
            }
        }
        System.out.println(ans/2);
    }
}
