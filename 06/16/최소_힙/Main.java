import java.awt.Point;
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> queue = new PriorityQueue<>();

        while(count-- > 0){
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            if(x == 0){
                if(queue.isEmpty()) System.out.println(0);
                else System.out.println(queue.poll());
            }else{
                queue.add(x);
            }
        }

        br.close();
    }
}
