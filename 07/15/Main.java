
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
//모든 경우의 수 구함(보물이 있을 수 있는)
//모든 경우의 수를 bfs돌려서 연결되어 있는지 확인
//연결되어 있으면 distinct 구해서 min과 비교 후 넣음

public class Main {
    static int N = 5, min = Integer.MAX_VALUE, star;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static List<Miro> pieces = new ArrayList<>();

    static char board[][] = new char[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<N; j++){
                board[i][j] = str.charAt(j);
                if(board[i][j] == '*'){
                    pieces.add(new Miro(i ,j));
                    board[i][j] = '.';
                }
            }
        }
        star = pieces.size();
        DFS(0, 0);

        System.out.println(min);
    }
    public static void DFS(int count, int distinct){
        if (distinct >= min) {
            return;
        }

        if(count == star) {
            if (BFS()) {
                min = Math.min(distinct, min);
            }
            return;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if (board[i][j] == '.') {
                    board[i][j] = '*';
                    int dist = Math.abs(pieces.get(count).x - i) + Math.abs(pieces.get(count).y - j);
                    DFS(count + 1, dist + distinct);
                    board[i][j] = '.';
                }
            }
        }
    }
    public static boolean BFS(){
        Queue<Miro> queue = new LinkedList<>();
        int[][] checked = new int[N][N];
        int cnt = 1;

        Miro piece = findStar();
        if(piece == null) return false;

        queue.add(piece);
        checked[piece.x][piece.y] = 1;

        //하나에서 star 만큼의 경우의 수 구하기
        while(!queue.isEmpty()){
            piece = queue.poll();

            for(int j=0; j<4; j++){
                int nextX = dx[j] + piece.x;
                int nextY = dy[j] + piece.y;

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N ) continue;
                if (board[nextX][nextY] == '.' || checked[nextX][nextY] > 0) continue;

                checked[nextX][nextY] = checked[piece.x][piece.y] + 1;
                queue.add(new Miro(nextX, nextY));
                cnt++;
            }
        }
        if(cnt == star) return true;
        return false;
    }
    public static Miro findStar(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if (board[i][j] == '*') return new Miro(i ,j);
            }
        }
        return null;
    }
}
