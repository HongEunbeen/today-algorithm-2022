import java.io.*;
import java.util.*;

public class Main {
    public static int N, cnt, start, end, num, sum;
    public static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList<>();
        N = Integer.parseInt(st.nextToken()); //수의 개수

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        for(int i=0; i<N; i++){
            num = list.get(i);
            start = 0;
            end = N-1;
            while(start < end){
                if(start == i) start += 1;
                else if(end == i) end -= 1;

                if(start == end) break;

                sum = list.get(start) + list.get(end);
                if(sum == num){
                    cnt += 1;
                    break;
                }
                else if(sum < num) start += 1;
                else end -= 1;
            }
        }

        System.out.println(cnt);
    }
}
