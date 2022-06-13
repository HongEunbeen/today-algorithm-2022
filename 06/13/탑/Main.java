import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<int[]> stack = new Stack<>();

        int n = Integer.parseInt(st.nextToken());
        int arr[] = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            int item = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()){
                if (stack.peek()[1] >= item) {
                    System.out.print(stack.peek()[0] + " ");
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()) System.out.print(0 + " ");
            stack.push(new int[]{i+1 ,item});
        }

    }
}
