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
    static int R, C, T;
    static List<Miro> smoke = new ArrayList<>();
    static List<Miro> air = new ArrayList<>();
    static int[][] room;
    static int[][] nextRoom;
    static int[] dx = {-1, 0, 1 ,0};
    static int[] dy = {0, 1, 0 ,-1};
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken()); // 세로(행)
        C = Integer.parseInt(st.nextToken()); // 가로(열)
        T = Integer.parseInt(st.nextToken()); // 시간

        room = new int[R+1][C+1];
        nextRoom = new int[R+1][C+1];

        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<C; j++){
                int input = Integer.parseInt(st.nextToken());
                room[i][j] = input;
                if(input == -1){
                    air.add(new Miro(i, j));
                } else if(input != 0){
                    smoke.add(new Miro(i, j));
                }
            }
        }

        while (T-- > 0){
            makeSmoke(); //미세먼지 발생
            cleanRoom();
            makeAir(); //공기 발생
            cleanRoom();
        }
        countSmoke();
    }
    public static void countSmoke(){
        int result = 0;
        for(Miro item : smoke){
            result += room[item.x][item.y];
        }
        System.out.println(result);
    }
    public static void makeSmoke(){
        for(Miro item : smoke){
            int current = room[item.x][item.y];
            int next = current/5;
            int cnt = 0;

            for(int i=0; i<4; i++){
                int nextX = item.x + dx[i];
                int nextY = item.y + dy[i];

                if(nextX < 0 || nextY < 0|| nextX >= R || nextY >= C) continue;
                if(room[nextX][nextY] == -1) continue;

                nextRoom[nextX][nextY] += next;
                cnt++;
            }
            nextRoom[item.x][item.y] += current-(next*cnt);
        }
    }
    public static void makeAir(){
        int index = 0;

        for(Miro item : smoke){
            nextRoom[item.x][item.y] = room[item.x][item.y];
        }

        for(Miro item : air){
            queue.clear();
            makeSmokeToRight(item);
            if(index == 0) makeSmokeToTopLast(item);
            else if(index == 1) makeSmokeToDownLast(item);
            makeSmokeToLeft(index);
            if(index == 0) makeSmokeToDownFirst(item);
            else if(index == 1) makeSmokeToTopFirst(item);

            if(nextRoom[item.x][item.y] > 0){
                nextRoom[item.x][item.y] = 0;
            }

            index++;
        }

    }
    public static void makeSmokeToRight(Miro air){
        nextRoom[air.x][1] = 0;
        for(int i=1; i<C; i++){
            if(i+1 == C){
                queue.add(room[air.x][i]);
                break;
            }
            nextRoom[air.x][i+1] = room[air.x][i];
        }
    }
    public static void makeSmokeToLeft(int index){
        int nextX = index == 0 ? 0 : R-1;
        for(int i=C-1; i>=0; i--){
            if(!queue.isEmpty()){
                room[nextX][i] = queue.poll();
            }
            else if(i == 0){
                queue.add(room[nextX][i]);
                break;
            }
            nextRoom[nextX][i-1] = room[nextX][i];
        }
    }
    public static void makeSmokeToDownFirst(Miro air){
        for (int i=0; i<air.x; i++) {
            if(!queue.isEmpty()){
                room[i][0] = queue.poll();
            }
            nextRoom[i+1][0] = room[i][0];
        }
    }
    public static void makeSmokeToDownLast(Miro air){
        for (int i=air.x; i<R; i++) {
            if(!queue.isEmpty()){
                room[i][C-1] = queue.poll();
            } else if(i+1 == R){
                queue.add(room[i][C-1]);
                break;
            }
            nextRoom[i+1][C-1] = room[i][C-1];
        }
    }
    public static void makeSmokeToTopFirst(Miro air){
        for (int i=R-1; i>air.x; i--) {
            if(!queue.isEmpty()){
                room[i][0] = queue.poll();
            }
            nextRoom[i-1][0] = room[i][0];
        }
    }
    public static void makeSmokeToTopLast(Miro air){
        for (int i=air.x; i>=0; i--) {
            if(!queue.isEmpty()){
                room[i][C-1] = queue.poll();
            }
            else if(i-1 < 0){
                queue.add(room[i][C-1]);
                break;
            }
            nextRoom[i-1][C-1] = room[i][C-1];
        }
    }
    public static void cleanRoom(){
        room = nextRoom.clone();

        for(Miro item : air){
            room[item.x][item.y] = -1;
        }

        nextRoom = new int[R+1][C+1];

        smoke.clear();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(room[i][j] >= 0) smoke.add(new Miro(i ,j));
            }
        }
    }
}
