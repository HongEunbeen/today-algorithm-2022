import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("");
        Stack<String> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        
        for(int i=0; i<input.length; i++){
           switch(input[i]){
            case "(":
                stack.push(input[i]);
                break;
            case ")":
                while(!stack.isEmpty() && !stack.peek().equals("(")) {
                    builder.append(stack.pop());
                }
                if(!stack.isEmpty()) stack.pop();
                break;
            case "*": case "/":
                while(!stack.isEmpty() && getLevel(stack.peek()) >= 2){
                    builder.append(stack.pop());
                }
                stack.push(input[i]);
                break;
            case "-": case "+":
                while(!stack.isEmpty() && getLevel(stack.peek()) >= 1){
                    builder.append(stack.pop());
                }
                stack.push(input[i]);
                break;
            default:
                builder.append(input[i]);
                break;
           }
        }

        while(!stack.isEmpty()){
            builder.append(stack.pop());
        }

        System.out.println(builder);
    }
    public static int getLevel(String str){
        if(str.equals("/") || str.equals("*")) return 2;
        else if(str.equals("+") || str.equals("-")) return 1;
        else return 0;
    }
}
