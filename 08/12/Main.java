import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder;
        Stack<String> stack = new Stack<>();

        String[] str = br.readLine().split("");
        String dump = br.readLine();

        for(int i=0; i<str.length; i++){
            stack.push(str[i]);
            
            if(dump.indexOf(str[i]) == dump.length()-1){
                builder = new StringBuilder();

                for(int j=0; j<dump.length(); j++){
                    builder.insert(0, stack.pop());
                }

                if(!builder.toString().equals(dump)){
                    for(int j=0; j<builder.length(); j++){
                        stack.push(builder.charAt(j) + "");
                    }
                }
            }
        }

        builder = new StringBuilder();
        while(!stack.isEmpty()){
            builder.append(stack.pop());
        }

        if(builder.isEmpty()) System.out.println("FRULA");
        else System.out.println(builder.reverse());

    }
}

