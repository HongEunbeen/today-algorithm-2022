
import java.io.*;
import java.util.*;

class Miro{
    int x, y, cnt, hasGram;

    Miro(int x, int y, int cnt, int hasGram)
    {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.hasGram = hasGram;
    }
}

public class Main {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int min = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());

        int arr[][] = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++)
                arr[i][j] = Integer.valueOf(st.nextToken());
        }
        BFS(arr, n, m, t);
        System.out.println(min == -1 ? "Fail" : min);
        br.close();
    }
    public static void BFS(int[][] arr, int N, int M, int T) {
        Queue<Miro> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];

        queue.add(new Miro(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Miro miro = queue.poll();

            if(miro.cnt > T) continue;
            //공주한테 도달
            if((miro.x == N-1) && (miro.y == M-1))
            {
                min = miro.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = miro.x + dx[i];
                int nextY = miro.y + dy[i];

                int hasGram = miro.hasGram;

                if ((nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)){
                    continue;
                }
                //검이 없을때
                if(hasGram == 0)
                {
                    if(visited[nextX][nextY][0] || arr[nextX][nextY] == 1) continue;
                    if( arr[nextX][nextY]  == 2) hasGram++;

                }
                //있을때
                else
                {
                    if(visited[nextX][nextY][1]) continue;
                }
                visited[nextX][nextY][hasGram] = true;
                queue.add(new Miro(nextX,nextY,miro.cnt+1,hasGram));

            }
        }
    }
}
