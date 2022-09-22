import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Main {
    static int N, M, result, max;
    static PriorityQueue<Integer> minusBooks, plusBooks;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //책
        M = Integer.parseInt(st.nextToken()); //들 수 있는 책
        minusBooks = new PriorityQueue<>();
        plusBooks = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int point = Integer.parseInt(st.nextToken()); //좌표
            if(point > 0){
                plusBooks.add(point);
            }
            else {
                minusBooks.add(point);
            }
            max = Math.max(max, Math.abs(point));
        }
        calcMinusDistinct();
        calcPlusDistinct();
        System.out.println(result-max);
    }
    public static void calcMinusDistinct(){
        while(!minusBooks.isEmpty()){
            result += minusBooks.peek() * -2;
            for(int i=0; i<M; i++){
                minusBooks.poll();
            }
        }
    }
    public static void calcPlusDistinct(){
        while(!plusBooks.isEmpty()){
            result += plusBooks.peek() * 2;
            for(int i=0; i<M; i++){
                plusBooks.poll();
            }
        }
    }
}
