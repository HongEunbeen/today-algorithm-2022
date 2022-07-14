
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
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[] symbol = {'R', 'G', 'B'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(br.readLine()); //그림

        char gridThreeColor[][] = new char[N][N];
        char gridTwoColor[][] = new char[N][N];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<N; j++){
                gridThreeColor[i][j] = str.charAt(j);
                gridTwoColor[i][j] = str.charAt(j) == 'G' ? 'R' : str.charAt(j);
            }
        }

        System.out.println(BFS(gridThreeColor) + " " + BFS(gridTwoColor));

    }
    public static int BFS(char board[][]){
        Queue<Miro> queue = new LinkedList<>();
        boolean checked[][] = new boolean[N][N];
        int cnt = 0;

        Miro color = new Miro(0,0);
        checked[0][0] = true;
        queue.add(color);

        while(!queue.isEmpty()){
            while(!queue.isEmpty()){
                color = queue.poll();

                for(int i=0; i<4; i++){
                    int newX = dx[i] + color.x;
                    int newY = dy[i] + color.y;

                    if(newX < 0 || newY < 0 || newX >= N || newY >= N ) continue;

                    if(!checked[newX][newY] && board[newX][newY] == board[color.x][color.y]){
                        queue.add(new Miro(newX, newY));
                        checked[newX][newY] = true;
                    }
                }
            }
            cnt++;
            color = findNewColor(checked);
            if(color.x == 0 && color.y == 0) break;
            queue.add(color);
        }
        return cnt;
    }
    static Miro findNewColor(boolean checked[][]){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!checked[i][j]){
                    Miro color = new Miro(i,j);
                    checked[i][j] = true;
                    return color;
                }
            }
        }
        return new Miro(0,0);
    }
}
