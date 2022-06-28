import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            long result = n <= 2 ? 1 : divide(n-2);
            System.out.println(result);
        }

        br.close();
    }
    private static long divide(int x) {
        if(x == 0) return 1;
        long divide_x = divide(x/2);
        return (divide_x * divide_x) * (x%2+1) % 1000000007;
    }
}
