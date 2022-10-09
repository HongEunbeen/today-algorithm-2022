import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
    public static int N, M, time;
    public static List<Integer> cranes, boxes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cranes = new ArrayList<>();
        boxes = new ArrayList<>();

        N = Integer.parseInt(br.readLine()); //크레인의 개수

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            cranes.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine()); //박스의 개수
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++){
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(cranes, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());

        if(cranes.get(0) < boxes.get(0)){
            System.out.println(-1);
            System.exit(0);
        }

        while (!boxes.isEmpty()) {
            int idx = 0;
            for (int i=0; i<N; i++) {
                if(boxes.isEmpty() || idx >= boxes.size()) break;

                if (boxes.get(idx) <= cranes.get(i)) {
                    boxes.remove(idx);
                } else {
                    i--;
                    idx++;
                }
            }
            time++;
        }

        System.out.println(time);
    }
}
