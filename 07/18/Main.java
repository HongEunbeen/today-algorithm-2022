import java.util.*;
import java.io.*;

class Miro {
    int x;
    int y;

    public Miro(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M ,cnt = 1;
    static int board[][][], checked[][];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Miro> cheeses = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M][2];
        checked = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                board[i][j][0] = Integer.parseInt(st.nextToken());
                board[i][j][1] = 0;
            }
        }


        // 가장자리부터 돌면서 1을 찾음
        // 1을 찾으면 1의 그룹 찾음
        // 1의 그룹의 2면 이상이 공기와 닿으면 0으로 바꿈

        BFS();
        System.out.println(cnt);

    }

    static void BFS() {
        Queue<Miro> queue = new LinkedList<>();
        Miro cheese = new Miro(0, 0);
        queue.add(cheese);

        while(!queue.isEmpty()){
            checked = new int[N][M];

            while (!queue.isEmpty()) {
                cheese = queue.poll();
                checked[cheese.x][cheese.y] = 1;
                setCheeseWithAir(cheese.x, cheese.y);

                for(int i=0; i<4; i++){
                    int nextX = cheese.x +dx[i];
                    int nextY = cheese.y +dy[i];

                    if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M ) continue;
                    //0이면 주변에 있는 1에 +1;
                    if(checked[nextX][nextY] > 0 || board[nextX][nextY][0] != 0) continue;

                    queue.add(new Miro(nextX, nextY));
                    checked[nextX][nextY] = checked[cheese.x][cheese.y] + 1;
                }
            }
            setCheeseState();
            if(!findCheese()) return;
            cheese = new Miro(0,0 );
            queue.add(cheese);
            cnt+=1;
        }
    }
    static boolean findCheese(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(board[i][j][0] == 1) return true;
            }
        }
        return false;
    }
    static void setCheeseState(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(board[i][j][1] > 1 && board[i][j][0] == 1)
                    board[i][j][0] = 0;
                else board[i][j][1] = 0;
            }
        }
    }
    static void setCheeseWithAir(int zeroX, int zeroY){
        for(int i=0; i<4; i++){
            int nextX = zeroX +dx[i];
            int nextY = zeroY +dy[i];

            if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M ) continue;
            if(board[nextX][nextY][0] == 1){
                board[nextX][nextY][1] += 1;
            }
        }
    }
}
