import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 소시지의 수
        M = Integer.parseInt(st.nextToken()); // 평론가의 수

        System.out.println(M - gcd(N, M));
    }
    public static int gcd(int x, int y){
        if(y == 0){
            return x;
        }
        else return gcd(y, x%y);
    }
}
