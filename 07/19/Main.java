import java.io.*;
import java.util.*;

class Miro {
    int x;
    int y;

    public Miro(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, board[][], max = 0, zeroCount = -3;
    static List<Miro> list = new ArrayList<>();

    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) zeroCount++;
                if(board[i][j] == 2) list.add(new Miro(i, j));
            }
        }
        DFS(0);

        System.out.println(max);

    }
    public static void DFS(int count){
        if(count == 3) {
            max = Math.max(zeroCount-BFS(), max);
            return;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    DFS(count + 1);
                    board[i][j] = 0;
                }
            }
        }
    }
    //벽 3개를 세울 수 있는 모든 경우의 수 탐색
    //bfs로 바이러스 감염 시킴
    //bfs 돌린 배열에서 0 갯수 max 인것 정답...!
    public static int BFS(){
        Queue<Miro> queue = new LinkedList<>();
        boolean checked[][] = new boolean[N][M];

        int cnt = 0;

        for(Miro miro : list){
            queue.add(miro);
            checked[miro.x][miro.y] = true;

            while(!queue.isEmpty()) {
                miro = queue.poll();

                for(int i=0; i<4; i++){
                    int nextX = miro.x + dx[i];
                    int nextY = miro.y + dy[i];

                    if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
                    if(checked[nextX][nextY] || board[nextX][nextY] != 0) continue;

                    checked[nextX][nextY] = true;
                    queue.add(new Miro(nextX, nextY));
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
