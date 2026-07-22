public class Queue {
    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public void enqueue(int value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }

        int value = front.value;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return value;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }

        return front.value;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void print() {
        System.out.print("Front -> ");
        Node currentNode = front;

        while (currentNode != null) {
            System.out.print(currentNode.value);

            if (currentNode.next != null) {
                System.out.print(", ");
            }

            currentNode = currentNode.next;
        }

        System.out.println();
    }
}
