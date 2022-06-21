import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long start = 1;
        long end = k;

        while(start < end) {
            long mid = (start + end) / 2;
            long count = 0;
            for(int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }
            if(k <= count) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        System.out.println(start);

        br.close();
    }
}
