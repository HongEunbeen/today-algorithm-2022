import java.io.*;
import java.util.*;

class Miro {
    int x;
    int y;
    int distinct;
    boolean drill;

    public Miro(int x, int y, int distinct, boolean drill) {
        this.x = x;
        this.y = y;
        this.drill = drill;
        this.distinct = distinct;
    }
}

public class Main {
    static int N, M, board[][];
    static int dx[] = {1, -1, 0 ,0};
    static int dy[] = {0, 0, 1,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                board[i][j] = str.charAt(j) - 48;
            }
        }

        System.out.println(BFS());
    }
    static int BFS(){
        Queue<Miro> queue = new LinkedList<>();
        boolean [][][] checked = new boolean[N][M][2];

        Miro miro = new Miro(0,0, 1, false);
        checked[miro.x][miro.y][0] = true;
        queue.add(miro);

        while(!queue.isEmpty()) {
            miro = queue.poll();

            if(miro.x == N-1 && miro.y == M-1) return miro.distinct;

            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + miro.x;
                int nextY = dy[i] + miro.y;

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;

                //벽이 아님
                if (board[nextX][nextY] == 0){
                    if(!miro.drill && !checked[nextX][nextY][0]){
                        queue.add(new Miro(nextX, nextY, miro.distinct + 1, false));
                        checked[nextX][nextY][0] = true;
                    }
                    else if(miro.drill && !checked[nextX][nextY][1]){
                        queue.add(new Miro(nextX, nextY, miro.distinct + 1, true));
                        checked[nextX][nextY][1] = true;
                    }
                }
                //벽
                else {
                    if(!miro.drill){
                        queue.add(new Miro(nextX, nextY,miro.distinct + 1, true));
                        checked[nextX][nextY][1] = true;
                    }
                }
            }
//            System.out.println("-----------");
//            for(int i=0; i<N; i++){
//                for(int j=0; j<M; j++){
//                    System.out.print(checked[i][j][0] ? 1 : 0);
//                }
//                System.out.print("     ");
//                for(int j=0; j<M; j++){
//                    System.out.print(checked[i][j][1] ? 1 : 0);
//                }
//                System.out.println();
//            }
        }
       return -1;
    }
}
