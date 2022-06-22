import java.io.*;
import java.util.*;

// bca라고 한다면, 유진이는 b, c, a, bb, bc, ba, cb, cc, ca, ab, ac, aa, bbb, bbc, .......

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        long count = 0;
        long control = 1;
        int leng = str.length();

        st = new StringTokenizer(br.readLine());
        String key = st.nextToken();


        if(key.length() == 1){
            System.out.println((str.indexOf(key) + 1)%900528);
        }
        else{
            for(int i=key.length()-1; i >= 0; i--){
                int index = str.lastIndexOf(key.charAt(i)) + 1;
                count = (count + control * index) %900528;
                control = control * leng % 900528;
            }
            System.out.println(count%900528);

        }
        br.close();
    }
}
