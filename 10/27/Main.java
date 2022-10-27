import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N ,CNT;
    static char[] arr = new char[2000];
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); //문자 수
        for(int i=0; i<N; i++){
            arr[i] = br.readLine().charAt(0);
        }

        int start = 0;
        int end = N-1;

        while(start < end){
            if(arr[start] < arr[end]){
                sb.append((char)arr[start++]);
                CNT++;
            } else if(arr[start] > arr[end]){
                sb.append((char)arr[end--]);
                CNT++;
            } else{
                int num = 1;
                boolean flag = false;
                while(start+num < end-num){
                    if(arr[start+num] == arr[end-num]){
                       num++;
                       continue;
                    }
                    if(arr[start+num] < arr[end-num]){
                        sb.append((char)arr[start++]);
                    } else{
                        sb.append((char)arr[end--]);
                    }
                    CNT++;
                    flag = true;
                    break;
                }
                
                if(!flag){
                    for(int i=start; i<end; i++){
                        sb.append((char)arr[i]);
                        CNT++;
                        if(CNT%80 == 0 && CNT > 0){
                            sb.append("\n");
                        }
                    }
                    break;
                }
            }

            if(CNT%80 == 0 && CNT > 0){
                sb.append("\n");
            }
        }

        sb.append((char)arr[start]);
        CNT++;
        if(CNT%80 == 0 && CNT > 0){
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
