
import java.io.*;
import java.util.*;

class MaxNum {
    int value;
    int cnt;

    public MaxNum(int value, int cnt) {
        this.value = value;
        this.cnt = cnt; //교환횟수
    }
}

public class Main {
    static int K, N, result = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        BFS();
        System.out.println(result);

    }
    public static void BFS(){
        Queue<MaxNum> queue = new LinkedList<>();
        boolean checked[][] = new boolean[1000001][K+1];

        MaxNum num = new MaxNum(N, 0);
        queue.add(num);
        checked[N][0] = true;

        while(!queue.isEmpty()) {
            num = queue.poll();

            if (num.cnt == K) {
                result = Math.max(result, num.value);
                continue;
            }

            int len = String.valueOf(num.value).length();

            for (int i=0; i<len-1; i++) {
                for (int j=i+1; j<len; j++) {
                    int next = swap(num.value, i , j);

                    if (next != -1 && !checked[next][num.cnt+1]) {
                        queue.add(new MaxNum(next, num.cnt+1));
                        checked[next][num.cnt+1] = true;
                    }
                }
            }
        }
    }
    public static int swap(int num, int i, int j){
        char[] numArr = String.valueOf(num).toCharArray();

        if (i == 0 && numArr[j] == '0') {
            return -1;
        }

        char tmp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = tmp;

        return Integer.parseInt(new String(numArr));
    }
}
