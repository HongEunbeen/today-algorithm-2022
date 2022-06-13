import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static  PriorityQueue<Edge> queue;
    static int arr[];
    static boolean[] checkList;
    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.weight, edge.weight);
        }
    }
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            int v = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());

            arr = new int[v+1];
            for(int i = 1; i <= v; i++) {
                arr[i]= i;
            }

            queue = new PriorityQueue<>();
            for(int i = 1; i <= e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()),
                    b = Integer.parseInt(st.nextToken()),
                    c = Integer.parseInt(st.nextToken());
                queue.add(new Edge(a, b, c));
            }

            checkList = new boolean[v+1];
            sb.append(MST());

            System.out.println(sb);
            br.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static long MST(){
        int sum = 0;
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            if(find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                sum += edge.weight;
            }
        }
        return sum;
    }
    public static void union(int from, int to){
        int value1 = find(from);
        int value2 = find(to);

        if(value1 < value2) arr[value2] = value1;
        else arr[value1] = value2;
    }
    public static int find(int n){
        if(arr[n] == n) return n;
        return arr[n] = find(arr[n]);
    }
}
