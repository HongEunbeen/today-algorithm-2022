import java.io.*;
import java.sql.Array;
import java.util.*;

class Alphabet implements Comparable<Alphabet>{
    long num;
    char alphabet;
    boolean isFirst;

    Alphabet(long num, char alphabet, boolean isFirst){
        this.num = num;
        this.alphabet = alphabet;
        this.isFirst = isFirst;
    }

    @Override
    public int compareTo(Alphabet o) {
        if(o.num < this.num) return -1;
        return 1;
    }
}
public class Main {
    public static int N;
    public static long result;
    public static List<String> list;
    public static List<Alphabet> alphabets;
    public static long[] arr = new long[10];
    public static boolean[] first = new boolean[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(arr, -1);

        alphabets = new ArrayList<>();
        list = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            String str = new StringBuilder(br.readLine()).reverse().toString();
            for(int j=0; j<str.length(); j++){
                int index = ((int)str.charAt(j)) - 65;
                arr[index] += (long) Math.pow(10, j);
                if(str.length()-1 == j){
                    first[index] = true;
                }
            }
            list.add(str);
        }

        for(int i=0; i<10; i++){
            alphabets.add(new Alphabet(arr[i], (char)(65+i), first[i]));
        }

        Collections.sort(alphabets);

       if(alphabets.get(9).isFirst){
           for(int i=8; i>=0; i--){
               if(!alphabets.get(i).isFirst) {
                    alphabets.add(alphabets.get(i));
                    alphabets.remove(i);
                    break;
               }
           }
       }

        int big = 9;
        for(int i=0; i<10; i++){
            int index = (int)(alphabets.get(i).alphabet-65);
            arr[index] = big--;
         }

        for (String str : list) {
            String sum = "";
            for(int j=str.length()-1; j>=0; j--){
                int index = ((int)str.charAt(j)) - 65;
                sum += arr[index];
            }
            result += Long.parseLong(sum);
        }

        System.out.println(result);
    }
}
