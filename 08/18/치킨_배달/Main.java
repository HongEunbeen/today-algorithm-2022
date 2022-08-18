import java.io.*;
import java.util.*;

class Miro {
    int x;
    int y;
    int dist;

    public Miro(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main { 
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static int N, M, board[][], result = Integer.MAX_VALUE;
    static List<Miro> houses, chickens;
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // N*N 마을
        M = Integer.parseInt(st.nextToken()); // 남길 치킨집

        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                checkBoard(i, j);
            }
        }

        calcChicken(0);
        System.out.println(result);
    }
    public static void checkBoard(int i, int j){
        if(board[i][j] == 1) {
            houses.add(new Miro(i, j, 0));
        }
        else if(board[i][j] == 2) {
            chickens.add(new Miro(i, j, 0));
            board[i][j] = 0;
        }
    }
    public static void calcChicken(int count){
        if(count == M){
            result = Math.min(getAllDist(), result);
            return;
        }
        
        for(Miro chicken : chickens){
            board[chicken.x][chicken.y] = 2;
            calcChicken(count + 1);
            board[chicken.x][chicken.y] = 0;
        }
    }
    public static int getAllDist(){
        int sum = 0;
        for(Miro house : houses){
            sum += getHouseDist(house);
        }
        return sum;
    }
    public static int getHouseDist(Miro house){
        Queue<Miro> queue = new LinkedList<Miro>();
        boolean checked[][] =new boolean[N][N];

        queue.add(house);
        checked[house.x][house.y] = true;
        
        while(!queue.isEmpty()){
            house = queue.poll();

            for(int i=0; i<4; i++){
                int nextX = house.x + dx[i];
                int nextY = house.y + dy[i];
                
                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || checked[nextX][nextY]) continue;
                if(board[nextX][nextY] == 2){
                    return house.dist+1;
                }
                queue.add(new Miro(nextX, nextY, house.dist+1));
                checked[nextX][nextY] = true;
            }
        }
        return 0;
    }
}
