import java.io.*;
import java.util.*;

class Button{
    int ADDH, ADDT, MINT, ADDO, MINO, Time;

    public Button(int ADDH, int ADDT, int MINT, int ADDO, int MINO, int time) {
        this.ADDH = ADDH;
        this.ADDT = ADDT;
        this.MINT = MINT;
        this.ADDO = ADDO;
        this.MINO = MINO;
        Time = time;
    }

    @Override
    public String toString() {
        return ADDT + " " + MINT + " " + ADDO + " " + MINO;
    }
}

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(br.readLine());

        Button[] cnt = new Button[61];
        bfs(cnt);

        while(n-- > 0){
            int t = Integer.parseInt(br.readLine());
            System.out.println(cnt[t%60].ADDH + (t/60) + " " + cnt[t%60].toString());
        }
    }
    public static void bfs(Button[] cnt){
        boolean[] visited = new boolean[61];
        Queue<Button> queue = new LinkedList<>();
        queue.add(new Button(0,0,0,0,0,0));

        while (!queue.isEmpty()) {
            Button current = queue.poll();

            if (current.Time >= 0 && current.Time <= 60 && (!visited[current.Time])) {
                visited[current.Time] = true;
                cnt[current.Time] = current;

                queue.add(new Button(current.ADDH,current.ADDT,current.MINT,current.ADDO,current.MINO + 1,current.Time - 1));
                queue.add(new Button(current.ADDH,current.ADDT,current.MINT,current.ADDO+ 1,current.MINO,current.Time + 1));
                queue.add(new Button(current.ADDH,current.ADDT,current.MINT+ 1,current.ADDO,current.MINO,current.Time - 10));
                queue.add(new Button(current.ADDH,current.ADDT+ 1,current.MINT,current.ADDO,current.MINO,current.Time + 10));
                queue.add(new Button(current.ADDH+ 1,current.ADDT,current.MINT,current.ADDO,current.MINO,current.Time + 60));
            }
        }

    }
}
