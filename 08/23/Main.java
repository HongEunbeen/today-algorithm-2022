import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Road implements Comparable<Road> {
    int x, y, price;

    Road(int x, int y, int price){
        this.x = x;
        this.y = y;
        this.price = price;
    }

    @Override
    public int compareTo(Road o)
    {
        return this.price - o.price;
    }
}

class Tree {
    int parent, rank;

    Tree(int parent, int rank){
        this.parent = parent;
        this.rank = rank;
    }
}

public class Main {
    static int N, M, INF = 987654321;
    static List<Road> roads;
    static List<Tree> trees;
    static int[] dist;
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); //집의 개수
        M = Integer.parseInt(st.nextToken()); //길의 개수

        init();
        for(int i=1; i<M+1; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            roads.add(new Road(x, y, price));
            trees.add(new Tree(i, 0));
        }

        Collections.sort(roads);
        kruskalMST();
        print();
    }
    public static void init(){
        dist = new int[N+1];
        roads = new ArrayList<>();
        trees = new ArrayList<>();

        roads.add(new Road(0, 0,0));
        trees.add(new Tree(0, 0));
    }
    public static void print()
    {
        int sum = dist[1];
        for (int i=2; i<N-1; i++){
            sum += dist[i];
        }
        System.out.println(sum);
    }
    public static int find(int idx)
    {
        Tree current = trees.get(idx);

        if (current.parent != idx){
            current.parent = find(current.parent);
        }
        return current.parent;
    }
    public static void union(int x, int y)
    {
        int xIndex = find(x);
        int yIndex = find(y);
 
        Tree xTree = trees.get(xIndex);
        Tree yTree = trees.get(yIndex);

        if (xTree.rank < yTree.rank) {
            xTree.parent = yIndex;
        }
        else if (yTree.rank > xTree.rank) {
            yTree.parent = xIndex;
        }
        else {
            yTree.parent = xIndex;
            xTree.rank++;
        }
    }
    public static void kruskalMST() {
        Iterator<Road> iter = roads.iterator();
        int idx = 1;
        while (iter.hasNext()) {
            Road road = iter.next();
            int x = find(road.x);
            int y = find(road.y);
            
            if (x != y) {
                dist[idx++] = road.price;
                union(x, y);
            }
        }
    }
}
