import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());

        while(count-- > 0) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            int a_sum = 0;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a > 0 && a <= 21) {
                for(int i=1; i <=6; i++){
                    a_sum += i;
                    if(a_sum >= a){
                        switch (i){
                            case 1 : sum += 500; break;
                            case 2 : sum += 300; break;
                            case 3 : sum += 200; break;
                            case 4 : sum += 50; break;
                            case 5 : sum += 30; break;
                            case 6 : sum += 10; break;
                        }
                        break;
                    }
                }
            }
            if(b > 0 && b <= 31){
                for(int i=1; i <=5; i++){
                    if(Math.pow(2, i) > b){
                        sum += Math.pow(2, 10-i);
                        break;
                    }
                }
            }
            if(sum > 0) sum *= 10000;
            System.out.println(sum);
        }
        br.close();
    }
}
