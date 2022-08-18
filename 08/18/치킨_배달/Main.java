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
    static int N, M, result = Integer.MAX_VALUE;
    static List<Miro> houses = new ArrayList<>();
    static List<Miro> chickens = new ArrayList<>();
    static int[] alive;
    static boolean[] checked;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // N*N 마을
        M = Integer.parseInt(st.nextToken()); // 남길 치킨집

        alive = new int[M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                int type = Integer.parseInt(st.nextToken());
                if(type == 1) {
                    houses.add(new Miro(i, j));
                }
                else if(type == 2) {
                    chickens.add(new Miro(i, j));
                }
            }
        }
        checked = new boolean[chickens.size()];

        calcChicken(0, 0);
        System.out.println(result);
    }
    public static void calcChicken(int count, int idx){
        if(count == M){
            result = Math.min(getMinDist(), result);
            return;
        }

        for(int i=idx; i<chickens.size(); i++){
            if(!checked[i]){
                checked[i] = true;
                alive[count] = i;
                calcChicken(count+1, i+1);
                checked[i] = false;
            }
        }
    }
    public static int getMinDist(){
        int sum = 0;
        for(Miro house : houses){
            int min = Integer.MAX_VALUE;
            for(int item : alive){
                Miro chicken = chickens.get(item);
                int dist = Math.abs(house.x-chicken.x) + Math.abs(house.y-chicken.y);

                min = Math.min(min, dist);
            }
            sum += min;
        }
        return sum;
    }
}
