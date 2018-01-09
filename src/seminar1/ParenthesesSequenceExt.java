package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import seminar1.collections.ArrayStack;

// DONE!!!

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        ArrayStack<Character> stack = new ArrayStack();
        for (int i = 0; i < sequence.length(); i++) {
            char nextEl = sequence.charAt(i);
            if(nextEl == '(' || nextEl == '{' || nextEl == '[')
                stack.push(nextEl);
            if(nextEl == ']' && !(stack.pop() == '[')) return false;
            if(nextEl == ')' && !(stack.pop() == '(')) return false;
            if(nextEl == '}' && !(stack.pop() == '{')) return false;
        }
        if(stack.size() != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
