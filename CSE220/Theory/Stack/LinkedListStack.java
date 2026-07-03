class Node {
    String value;
    Node next;

    Node(String value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedListStack {

    private Node top;

    public LinkedListStack() {
        top = null;
    }

    public void push(String value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    public String pop() {
        if (top == null) {
            System.out.println("Stack is empty!");
            return null;
        }

        String poppedValue = top.value;
        top = top.next;
        return poppedValue;
    }

    public String peek() {
        if (top == null) {
            System.out.println("Stack is empty!");
            return null;
        }
        return top.value;
    }

    public void print() {
        if (top == null) {
            System.out.println("Empty Stack!");
            return;
        }
        System.out.println("Stack: ");
        Node current = top;
        while (current != null) {
            if (current == top) {
                System.out.println(current.value + " <-- top");
            } else {
                System.out.println(current.value);
            }
            current = current.next;
        }
    }

    public boolean isBalanced(String expression) {

        LinkedListStack stack = new LinkedListStack();

        for (int i = 0; i < expression.length(); i++) {
            char item = expression.charAt(i);

            if (item == '(' || 
                item == '{' || 
                item == '[') 
            {
                stack.push(String.valueOf(item));
            } 
            
            else if (item == ')' || 
                     item == '}' || 
                     item == ']') 
            {
                if (stack.top == null) {
                    return false;
                }
                String poppedValue = stack.pop();
                char value = poppedValue.charAt(0);

                if (item == '(' && value != ')' ||
                    item == '{' && value != '}' ||
                    item == '[' && value != ']'){
                    return false;
                }
            }

        }
        boolean isEmpty = false;
        if (stack.top == null) {
            isEmpty = true;
        }   
        return isEmpty;
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.print();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.print();
        System.out.println(stack.pop());
        stack.print();
        boolean balanced = stack.isBalanced("()())");
        System.out.println(balanced);
    }
}