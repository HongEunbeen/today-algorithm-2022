import java.awt.Point;
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> queue = new PriorityQueue<>(count, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1) > Math.abs(o2)) return 1;
                else if(Math.abs(o1) < Math.abs(o2)) return -1;
                else if(Math.abs(o1) == Math.abs(o2)){
                    if(o1 > o2) return 1;
                    else return -1;
                }
                return 0;
            }
        });

        while(count-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
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
