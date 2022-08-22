import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static long N;
    static int V = 6;
    static long[] calc = new long[3];
    static int[] dices;
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        dices = new int[V];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<V; i++){
            dices[i] = Integer.parseInt(st.nextToken());
        }

        initDices();

        long result = 0;
        if(N == 1){
            result = calcOneDice();
        } else if(N > 1){
            result += calc[0] * getOneDice();
            result += calc[1] * getTwoDice();
            result += calc[2] * getThreeDice();
        }

        System.out.println(result);
    }
    public static void initDices(){
        calc[0] = 5 * (N - 2) * (N - 2) + 4 * (N - 2);
        calc[1] = 8 * N - 12;
        calc[2] = 4;
    }
    public static int calcOneDice(){
        Arrays.sort(dices);
        int sum = 0;
        for(int i=0; i<V-1; i++){
            sum += dices[i];
        }
        return sum;
    }
    public static long getOneDice(){
        long min = dices[0];
        for(int i=1; i<V; i++){
            min = Math.min(min, dices[i]);
        }
        return min;
    }
    public static long getTwoDice(){
        long min = Long.MAX_VALUE;
        for(int i=0; i<V; i++){
            for(int j=i+1; j<V; j++){
                if(i+j != V-1){
                    min = Math.min(min, dices[i] + dices[j]);
                }
            }
        }
        return min;
    }
    public static int getThreeDice(){
        int sum = 0, last = V-1;
        for (int i=0; i<3; i++) {
            sum += Math.min(dices[i], dices[last-i]);
        }
        return sum;
    }
}
