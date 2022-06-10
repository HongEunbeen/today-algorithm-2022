import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            int t = Integer.parseInt(st.nextToken());

            while(t-- > 0) {
                st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());

                while (m-- > 0) {
                    br.readLine();
                }
                sb.append((n - 1) + "\n");
            }
            System.out.println(sb);

            br.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
