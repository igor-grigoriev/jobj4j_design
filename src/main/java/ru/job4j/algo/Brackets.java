package ru.job4j.algo;

import java.util.Stack;

public class Brackets {

    public boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Stack<String> stack = new Stack<>();
        for (String str : s.split("")) {
            switch (str) {
                case "(":
                    stack.push(")");
                    break;
                case "[":
                    stack.push("]");
                    break;
                case "{":
                    stack.push("}");
                    break;
                default:
                    if (!stack.isEmpty() && str.equals(stack.peek())) {
                        stack.pop();
                    } else {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

}