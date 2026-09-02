class DFSStackNode {
    int value;
    DFSStackNode next;

    public DFSStackNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class DFSStack {
    DFSStackNode top;

    public void push(int value) {
        DFSStackNode newNode = new DFSStackNode(value);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }

        int value = top.value;
        top = top.next;
        return value;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
