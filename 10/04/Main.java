import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int sum = 9;
    static int N, max, result;
    static String[] arr;
    static Map<Character, Integer> map;
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); //단어의 개수
        arr = new String[N];
        map = new HashMap<>();

        for(int i=0; i<N; i++){
            sb = new StringBuilder(br.readLine());
            arr[i] = sb.reverse().toString();
            max = Math.max(arr[i].length()-1, max);
        }

        Arrays.sort(arr, (s1, s2) -> s2.length() - s1.length());

        for (int i=max; i>=0; i--) {
            for (int j=0; j<N; j++) {
                if (i >= arr[j].length()) break;

                char current = arr[j].charAt(i);
                int pow = (int) Math.pow(10, i);

                if(map.containsKey(current)){
                    pow += map.get(current);
                }

                map.put(current, pow);
            }
        }

        Iterator<Character> iter = map.keySet().iterator();
        List<Integer> list = new ArrayList<>();

        while (iter.hasNext()) {
            list.add(map.get(iter.next()));
        }
        
        Collections.sort(list, (s1, s2) -> s2 - s1);
        
        for (Integer num : list) {
            result += num * sum--;
        }
        
        System.out.println(result);
    }
}
