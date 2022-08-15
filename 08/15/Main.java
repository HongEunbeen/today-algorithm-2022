import java.io.*;
import java.util.*;

public class Main {
    public static int[] inArr, postArr, indexArr;
    public static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        inArr = new int[N+1];
        postArr = new int[N+1];
        indexArr = new int[N+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++){
            inArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++){
            postArr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i <=N; i++) {
            indexArr[inArr[i]] = i;
        }

        preOrder(1, N, 1, N);
        System.out.println(sb.toString());
    }
    public static void preOrder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        int root = postArr[postEnd];
        int rootI = indexArr[root];
        int left = rootI-inStart;

        sb.append(root + " ");

        //left
        preOrder(inStart, (rootI-1), postStart, (postStart+left-1));
        //right
        preOrder((rootI+1), inEnd, (postStart+left), (postEnd-1));
    }
}

