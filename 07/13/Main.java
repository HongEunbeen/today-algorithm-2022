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
    static int N, M, start, end;
    static int checked[][] = new int[10][10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //사다리 수
        M = Integer.parseInt(st.nextToken()); //뱀 수

        int board[][] = new int[10][10];

        for(int i=0; i<(N+M); i++){
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken());

            board[start/10][start%10] = end;
        }

        BFS(board);

        System.out.println(checked[9][9] - 1);
    }
    public static void BFS(int board[][]){

        Queue<Miro> queue = new LinkedList<>();
        Miro point = new Miro(0, 0);
        checked[0][0] = 1;
        queue.add(point);

        while(!queue.isEmpty()){
            point = queue.poll();

            for(int i=1; i<=6; i++){
                int num = ((point.x * 10) + (point.y + 1) + i) - 1;
                int newX = num/10;
                int newY = num%10;

                if(board[newX][newY] != 0){
                    num = board[newX][newY] - 1;
                    newX = num/10;
                    newY = num%10;
                }

                if(newX < 10 && newY < 10 && newX >=0 && newY >= 0 && checked[newX][newY] == 0){
                    queue.add(new Miro(newX, newY));
                    checked[newX][newY] = checked[point.x][point.y] + 1;
                }

                if(newX == 9 && newY == 9) return;
            }
        }
    }
}
