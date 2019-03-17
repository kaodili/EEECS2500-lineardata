import java.util.*;

public class Compound_logic {
    static String statement;
    static Stack<String> operators = new Stack();
    static Stack<String> operands = new Stack();
    static LinkedList<String> steps = new LinkedList<>();
    static LinkedList<String> variables = new LinkedList<>();
    
    public static class Stack<T> {
        T[] array = (T[]) new Object[200];
        int count;

        public Stack() {
            count = 0;
        }
        public boolean isEmpty() {
            return count == 0;
        }
        public T peek() throws EmptyStackException {
            if (count == 0) {
                throw new EmptyStackException();
            } else 
                return array[count - 1];
        }
         public T pop() throws EmptyStackException  {
            if (count == 0) {
                throw new EmptyStackException();
            } else
                array[count] = null;
                count--;
                return array[count];
        }
        public void push(T c) {
            array[count] = c;
            count++;
        }
        public void empty() {
            for(int i = 0; i < 200; i++) {
                array[i]=null;
                count = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        ArrayList<Character> propositions = new ArrayList<>();
        System.out.println("Enter a logic statement below: ");
        System.out.println("(Note: Use & for logical AND, || for logical OR, > for IMPLICATION, = for BICONDITIONAL)");
        statement = stdin.nextLine();
        for (int i = 0; i < statement.length(); i++) {
            if(!propositions.contains(statement.charAt(i))) {
                if((0x40<statement.charAt(i) && (statement.charAt(i)<0x5B)))
                        propositions.add((statement.charAt(i)));
                else if ((0x60<statement.charAt(i)) && (statement.charAt(i)<0x7b))
                    propositions.add((char)((statement.charAt(i))));
            }
        }
        
        int n = propositions.size();
        infixToPostfix(statement);
        evalExpression();
        logicTable(propositions, steps); 
    }
    
    public static void infixToPostfix(String input) {
        String[] token = input.split("(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])");
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                variables.add(String.valueOf(c));
            } else if (c== '(') {
                operators.push(String.valueOf(c));
            } else if (c == ')')  {
                while (!operators.isEmpty() && (!operators.peek().equals("("))) {
                  variables.add((String) operators.pop());
                } if (!operators.isEmpty() && (operators.peek().equals(")")) ) {
                    System.out.println("Invalid Expression");
                } else operators.pop();
            } else {
                operators.push(String.valueOf(c));
            }  
        }
        while (!operators.isEmpty()) {
            variables.add(operators.pop());
        }
    }
    
    public static void evalExpression() {
        LinkedList<String> variables2 = new LinkedList<>(variables);
        for (int i = 0; i < variables2.size(); i++) {
            if (Character.isLetter((variables2.get(i).charAt(0)))) {
                operands.push(variables2.get(i));
            }  if ("&".equals(variables2.get(i)) ||"|".equals(variables2.get(i)) || 
                    "!".equals(variables2.get(i)) || ">".equals(variables2.get(i)) || "=".equals(variables.get(i))) {
               String val1 = operands.pop();  
               String val2 = operands.pop();  
               operands.push(val2 + variables2.get(i).toString() + val1);
               steps.add(val2 + variables2.get(i).toString() + val1);
            } 
        }  
    }
    public static String[][] logicTable(ArrayList<Character> propositions, LinkedList<String> steps) {
        int r=propositions.size();
        String[][] tt;
        boolean x;
        tt = new String[0][0];
        int rows = (int) Math.pow(2, r);
        for(char c: propositions) {
            System.out.print(c+"\t");
        }
        for(String s: steps) {
            System.out.print(s+"\t");
        }
        System.out.println();
        operands.empty();
        for (int i = 0; i < rows; i++) {
            HashMap<String, Boolean> func = new HashMap<>();
            for (int j = r - 1; j >= 0; j--) {
                func.put(propositions.get(r-1-j).toString(), (((i)/(int)Math.pow(2,j)))%2!=0);
                System.out.print(((((i)/(int)Math.pow(2,j)))%2!=0) +"\t");
            }
            LinkedList<String> variables2 = new LinkedList<>(variables);
            for (int j = 0; j < variables2.size(); j++) {
                if (Character.isLetter((variables2.get(j).charAt(0)))) {
                    operands.push(variables2.get(j));
                }  if ("&".equals(variables2.get(j)) ||"|".equals(variables2.get(j)) 
                        || ">".equals(variables2.get(j)) || "=".equals(variables2.get(j))) {
                   String val1 = operands.pop();  
                   String val2 = operands.pop();  
//                   if ("!".equals(variables2.get(j))) {
//                       boolean y = implementLogic2(Boolean.valueOf(variables2.get(j)));
//                     x = implementLogic(func.get(val2), func.get(val1), String.valueOf(y));
//                   } else
                    x = implementLogic(func.get(val2), func.get(val1), variables2.get(j));
                   
                   func.put(val2 + variables2.get(j).toString() + val1, x);
                   operands.push(val2 + variables2.get(j).toString() + val1);
                   System.out.print(x+"\t");
                } 
            } System.out.println();
        }
        return tt;
    }
    public static boolean implementLogic(boolean a, boolean b, String op) {
        switch (op) {
            case "&":   return (a) && (b); 
            case "|":   return a || b; 
            case ">":   return !a || b;
            case "=":   return ((!a && b) || (a && !b));
        }
        return false;
    }
    public static boolean implementLogic2(boolean a) {
         return !a;
    }
}
