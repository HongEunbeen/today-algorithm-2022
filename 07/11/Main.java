
import java.io.*;
import java.util.*;

class Miro{
    public int x;
    public int y;
    public int z;

    public Miro(int z, int x, int y) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {
    static int N, M, H;
    static int[] dx = { -1, 0, 1, 0, 0, 0};
    static int[] dy =  {0, 1, 0, -1, 0, 0};
    static int[] dz =  {0, 0, 0, 0, 1, -1};

    static Queue<Miro> queue = new LinkedList<>();

    static int day = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken()); //가로
        N = Integer.parseInt(st.nextToken()); //세로
        H = Integer.parseInt(st.nextToken()); //높이

        int[][][] tomato = new int[H][N][M];

        for(int i=0; i<H; i++) {
            for(int j=0; j<N; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int z=0; z<M; z++) {
                    tomato[i][j][z] = Integer.parseInt(st.nextToken());
                    if(tomato[i][j][z]==1)  queue.add(new Miro(i, j, z));
                }
            }
        }

        if(queue.size() == N*H*H){
            day = 0;
        }
        else{
            BFS(tomato);
            for(int i=0; i<H; i++){
                for(int j=0; j<N; j++){
                   for(int z=0; z <M; z++){
                       if(tomato[i][j][z] == 0){
                           System.out.println(-1);
                           System.exit(0);
                       }
                       day = Math.max(day, tomato[i][j][z]);
                   }
                }
            }
        }
        System.out.println(day-1);
    }
    public static void BFS(int[][][] arr) {
        while (!queue.isEmpty()) {
            Miro tomato = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nextZ = tomato.z + dz[i];
                int nextX = tomato.x + dx[i];
                int nextY = tomato.y + dy[i];

                if (nextX < 0 || nextY < 0 || nextZ < 0 ||
                        nextX >= N || nextY >= M || nextZ >= H) continue;
                if (arr[nextZ][nextX][nextY] == 0){
                    queue.add(new Miro(nextZ, nextX, nextY));
                    arr[nextZ][nextX][nextY] = arr[tomato.z][tomato.x][tomato.y] + 1;
                }
            }
        }
    }
}
