import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
    public static int N, M, L, result;
    public static List<Integer> list;
    public static Map<Integer, Integer> distinct;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        list = new ArrayList<>();
        distinct = new HashMap<>();

        N = Integer.parseInt(st.nextToken()); // 현재 휴개소 개수
        M = Integer.parseInt(st.nextToken()); // 추가 휴개소 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로 길이

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.add(0);
        list.add(L);
        Collections.sort(list);

        int start = 1, end = L;
        result = L;
        while (start <= end) {
            int mid = (start+end)/2;
            int count = 0;

            for (int i = 1; i < list.size(); i++){
                count += (list.get(i)-list.get(i-1)-1) / mid;
            }

            if (count > M) {
                start = mid+1;
            }
            else {
                end = mid-1;
            }
        }
        System.out.println(start);
    }
}
