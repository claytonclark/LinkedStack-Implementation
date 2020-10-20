public class LinkedStackTest {
    public static void main(String[] args){
        String infix = "a*b/(c-a)+d*e";
        System.out.println("Infix form: " + infix);
        String postfix = convertToPostfix(infix);
        System.out.println("Postfix form: " + postfix);
    }

    public static String convertToPostfix(String infix){
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        String postfix = "";
        int index = 0;
        while(index < infix.length()){  // keep looping as long as we have characters left in string
            char nextCharacter = infix.charAt(index);
            switch(nextCharacter){
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
                    while(!operatorStack.isEmpty() && prescedence(nextCharacter) <= prescedence(operatorStack.peek())){ // check the prescedence of each char and compare them
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')':
                    char topOperator = operatorStack.pop();
                    while(topOperator != '('){          // pop all operators until you see a '('
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                    break;
                case ' ':
                    break;
                default:
                    postfix += nextCharacter;           // default assumes it is a variable
                    break;
            }
            index++;  // move to next char in infix string
        }
        while(!operatorStack.isEmpty()){                // pop all remaining operators and add to end of postfix
            char topOperator = operatorStack.pop();
            postfix += topOperator;
        }
        return postfix;     // return postfix string
    }
    private static int prescedence(char ch)  // sets prescedence of operations
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
}
