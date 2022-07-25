import java.io.*;
import java.util.*;

public class Main {
    static int N, arr[], min = Integer.MAX_VALUE;
    static int lSnow, aSnow, mid, start, end;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){ 
                lSnow = arr[i] + arr[j]; 
              
                start = 0, end = N-1;
                while(start <= end){
                    if(start == i || start == j) start++;
                    if(end == i || end == j) end--;

                    aSnow = arr[start] + arr[end];
                    min = Math.min(min, Math.abs(aSnow-lSnow));
                    
                    mid = (start + end) / 2;
                    if(lSnow > aSnow) start = mid + 1;
                    else if(lSnow < aSnow) end = mid - 1;
                    else{
                        System.out.println(min);
                        System.exit(0);
                    }
                }
            }
        }
        System.out.println(min);
    }
}
