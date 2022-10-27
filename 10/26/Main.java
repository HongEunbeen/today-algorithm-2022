import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Point{
    int x, y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Load{
    Point leftTop, rightBottom;
    int width, height;

    Load(Point leftTop, Point rightBottom){
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
        this.width = rightBottom.x-leftTop.x;
        this.height = rightBottom.y-leftTop.y;
    }
}
public class Main {
    static int N, P, x, y;
    static int[][] map = new int[110][110];
    static int[] result= new int[100000];
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); //나무의 수
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
        }

        P = Integer.parseInt(br.readLine()); //산책길 개수
        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            Point leftTop = new Point(x, y);

            x = Integer.parseInt(st.nextToken()) + 1;
            y = Integer.parseInt(st.nextToken()) + 1;
            Point rightBottom = new Point(x, y);

            result[i] = removeTrees(new Load(leftTop, rightBottom));
        }

        for(int i=0; i<P; i++){
            System.out.println(result[i]);
        }
        
    }
    public static int removeTrees(Load load){
        int count = 0;
        int start = 0;
        int end = load.width*load.height -1;

        while(start<=end) {
            int i = start/load.height + load.leftTop.y;
            int j = start%load.height + load.leftTop.x;

            if(map[i][j] == 1) {
                count += 1;
            }

            if(i != load.leftTop.y && i != load.rightBottom.y && j != load.rightBottom.x-1){
                start += load.width-1;
            }
            else{
                start++;
            }
        }

        return count;
    }
}
