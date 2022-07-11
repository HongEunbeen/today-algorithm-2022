import java.io.*;
import java.util.*;

class Miro{
    public int x;
    public int y;
    public int dist;

    public Miro(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    static int N;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy =  {0, 1, 0, -1};

    static int time = 0, weight = 2, buffer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //세로, 가로
        Miro shark = new Miro(0,0, 0);
        int[][] fish = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                fish[i][j] = Integer.parseInt(st.nextToken());
                if(fish[i][j] == 9){
                    shark.x = i;
                    shark.y = j;
                    shark.dist = 0;
                }
            }
        }

        BFS(fish,shark);

        System.out.println(time);

    }
    public static void BFS(int[][] arr, Miro shark) {
        Queue<Miro> queue = new LinkedList<>();
        queue.add(shark);
        arr[shark.x][shark.y] = 0;

        while(true){
            ArrayList<Miro> fish = new ArrayList<>();
            int[][] dist = new int[N][N];

            while (!queue.isEmpty()) {
                shark = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = shark.x + dx[i];
                    int nextY = shark.y + dy[i];
                    int distinct = Math.abs(shark.x - nextX) + Math.abs(shark.y - nextY);

                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                    if (weight < arr[nextX][nextY] || dist[nextX][nextY] != 0) continue;

                    dist[nextX][nextY] = dist[shark.x][shark.y] + 1;
                    queue.add(new Miro(nextX, nextY, dist[nextX][nextY]));
                    if(arr[nextX][nextY] != 0 && arr[nextX][nextY] < weight){
                        fish.add(new Miro(nextX, nextY, dist[nextX][nextY]));
                    }
                }
            }

            //종료 조건 : 먹을 수 있는 생선이 없다면 엄마에게...
            if(fish.size() == 0) return;

            Miro meal = fish.get(0);
            for(int i=1; i<fish.size(); i++){
                //거리가 가장 가까운 물고기
                Miro temp = fish.get(i);

                if(meal.dist < temp.dist) continue;
                else if(meal.dist > temp.dist) {
                    meal = temp;
                }
                //거리에 가까운 물고기가 많다면..
                else{
                    if(meal.x > temp.x){ //가장 위에 있는 물고기
                        meal = temp;
                    }
                    else if(meal.x == temp.x && meal.y > temp.y){ //가장 왼쪽에 있는 물고기
                        meal = temp;
                    }
                }
            }

            if(weight == ++buffer) {
                weight++;
                buffer = 0;
            }
            arr[meal.x][meal.y] = 0;
            time += meal.dist;

            //다음 상어 위치
            queue.add(new Miro(meal.x, meal.y, 0));
        }
    }
}
