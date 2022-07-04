import java.io.*;
import java.util.*;


public class Main {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };


    static int[][] graph = new int[25][25];
    static boolean[] alphabet = new boolean[26];

    static int R;
    static int C;
    static int cnt = 1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                graph[i][j] = str.charAt(j) - 'A';
            }
        }

        DFS(0, 0, 0);

        System.out.println(cnt);

    }
    public static void DFS(int x, int y, int count) {
        if(alphabet[graph[x][y]]) {
            cnt = Math.max(cnt, count);
            return;
        }
        alphabet[graph[x][y]] = true;
        for(int i=0; i<4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];

            if(newX >=0 && newY >=0 && newX < R && newY < C){
                DFS(newX, newY, count + 1);
            }
        }
        alphabet[graph[x][y]] = false;
    }
}
