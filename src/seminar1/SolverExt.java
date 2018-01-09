package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import seminar1.collections.LinkedStack;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static void calcOneOp(char c, LinkedStack<Double> stack){
        if(c == PLUS)
            stack.push(stack.pop() + stack.pop());
        if(c == MINUS)
            stack.push(- stack.pop() + stack.pop());
        if(c == TIMES)
            stack.push(stack.pop() * stack.pop());
        if(c == DIVISION) {
            double a = stack.pop();
            double b = stack.pop();
            stack.push(b / a);
        }
    }

    private static double evaluate(String[] values) {
        /* TODO: implement it */
        LinkedStack<Character> opStack = new LinkedStack<>();
        LinkedStack<Double> argStack = new LinkedStack<>();
        for (int i = 0; i < values.length; i++) {
            if(values[i].charAt(0) == LEFT_PAREN ||
                    values[i].charAt(0) == PLUS ||
                    values[i].charAt(0) == MINUS ||
                    values[i].charAt(0) == TIMES ||
                    values[i].charAt(0) == DIVISION) {
                opStack.push(values[i].charAt(0));
                continue;
            }
            if(values[i].charAt(0) == RIGHT_PAREN) {
                char c = opStack.pop();
                while(c != LEFT_PAREN){
                    calcOneOp(c, argStack);
                    c = opStack.pop();
                }

                continue;
            }
            argStack.push(Double.parseDouble(values[i]));

        }
        while(opStack.size() > 0){
            calcOneOp(opStack.pop(), argStack);
        }
        return argStack.pop();
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
