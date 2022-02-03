package com.vmotors;

import java.util.ArrayList;
import java.util.Stack;

public class Abc {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("5");
        list.add("2");
        list.add("C");
        list.add("D");
        list.add("+");

        Stack<String> stack = new Stack<>();

        for (String s : list) {
            if(s.equals("C")){
                stack.pop();
            }else if(s.equals("D")){
                stack.push(String.valueOf(Integer.valueOf(stack.peek())*2));
            }else if(s.equals("+")){
                String a = (String) stack.pop();
                String b = (String) stack.peek();
                String c = String.valueOf(Integer.parseInt(a)+Integer.parseInt(b));
                stack.push(a);
                stack.push(c);
            }else{
                stack.push(s);
            }
        }
        int result = 0;
        while(!stack.empty()){
            String a = (String) stack.pop();
            result = result + Integer.valueOf(a);
        }

        System.out.println(result);
    }
}
