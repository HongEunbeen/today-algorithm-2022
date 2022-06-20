import java.awt.Point;
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        Stack<Character> stack = new Stack<>();
        int sum = 0;
        int flagValue = 1;

        for(int i=0; i< str.length(); i++){
            char item = str.charAt(i);
            if(item == '('){
                flagValue *= 2;
                stack.push(item);
            }
            else if(item == '['){
                flagValue *= 3;
                stack.push(item);
            }
            else if(item == ')'){
                if(stack.isEmpty() || stack.peek() != '(') {
                    sum = 0;
                    break;
                } else if(str.charAt(i-1) == '(') {
                    sum += flagValue;
                }
                flagValue /=2;
                stack.pop();
            }
            else if(item == ']'){
                if(stack.isEmpty() || stack.peek() != '[') {
                    sum = 0;
                    break;
                } else if(str.charAt(i-1) == '[') {
                    sum += flagValue;
                }
                flagValue /=3;
                stack.pop();
            }
        }

        if(!stack.isEmpty()) System.out.println(0);
        else System.out.println(sum);

        br.close();
    }
}
