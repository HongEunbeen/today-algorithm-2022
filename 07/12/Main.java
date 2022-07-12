import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static char[] symbols = {'+', '-', '*'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //수빈이가 있는 위치
        K = Integer.parseInt(st.nextToken()); //동생의 위치

        if (N == K) {
            System.out.println(0);
        } else {
            BFS(N);
        }

    }
    public static void BFS(int num){
        Queue<Integer> queue = new LinkedList<>();
        int[] checked = new int[100001];

        queue.add(num);
        checked[num] = 1;

        while(!queue.isEmpty()){
            num = queue.poll();

            for(int i=0; i<symbols.length; i++){
                int nextNum = Operate(symbols[i], num);

                if (nextNum == K) {
                    System.out.println(checked[num]);
                    return;
                }

                if (nextNum >= 0 && nextNum < checked.length && checked[nextNum] == 0) {
                    queue.add(nextNum);
                    checked[nextNum] = checked[num] + 1;
                }
            }
        }
    }
    public static int Operate(char symbol, int num){
        switch (symbol){
            case '+' : return num + 1;
            case '-' : return num - 1;
            case '*' : return num * 2;
        }
        return 0;
    }
}
