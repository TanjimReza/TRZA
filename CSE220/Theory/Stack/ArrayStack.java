
public class ArrayStack {

    private String[] stack;
    private int top;

    public ArrayStack(int size) {
        if (size <= 0) {
            System.out.println("Stack size must be greater than 0");
            return;
        }
        stack = new String[size];
        top = -1;
    }

    public void push(String value) {
        if (top == stack.length - 1) {
            System.out.println("Stack is full!!");
            return;
        }
        top++;
        stack[top] = value;

    }

    public String pop() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return null;
        }

        String poppedValue = stack[top];
        stack[top] = null;
        top--;
        return poppedValue;
    }

    public String peek() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack[top];
    }

    public void print() {
        if (top == -1) {
            System.out.println("Empty Stack!");
            return;
        }
        System.out.println("Stack: ");
        for (int i = top; i >= 0; i--) {
            if (i == top) {
                System.out.println(stack[i] + " <-- top [" + top + "]");
            } else {
                System.out.println(stack[i]);
            }
        }
    }

    public boolean isBalanced(String expression) {
        
        int lengthOfExpression = expression.length();
        ArrayStack stack = new ArrayStack(lengthOfExpression);

        for (int i = 0; i < lengthOfExpression; i++) {
            char item = expression.charAt(i);

            if (item == '(' || item == '{' || item == '[') {
                stack.push(String.valueOf(item));
            } else if (item == ')' || item == '}' || item == ']') {
                if (stack.top == -1) {
                    return false;
                }
                String poppedValue = stack.pop();
                char value = poppedValue.charAt(0);

                if (value == '(' && item != ')' ||
                    value == '{' && item != '}' ||
                    value == '[' && item != ']'){
                    return false;
                }
            }

        }

        return stack.top == -1;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(6);
        stack.print();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.print();
        System.out.println(stack.pop());
        stack.print();
        boolean balanced = stack.isBalanced("()(()");
        System.out.println(balanced);
    }
}
