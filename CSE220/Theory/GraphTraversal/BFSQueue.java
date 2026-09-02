class BFSQueueNode {
    int value;
    BFSQueueNode next;

    public BFSQueueNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class BFSQueue {
    BFSQueueNode front;
    BFSQueueNode rear;

    public void enqueue(int value) {
        BFSQueueNode newNode = new BFSQueueNode(value);

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

    public boolean isEmpty() {
        return front == null;
    }
}
