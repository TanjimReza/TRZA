public class QueueQuiz {
    public static Queue reverseOdd(Queue q) {
        Stack oddValues = new Stack();

        // Remove every value from the queue and keep only the odd ones.
        while (!q.isEmpty()) {
            int value = q.dequeue();

            if (value % 2 != 0) {
                oddValues.push(value);
            }
        }

        // Popping reverses the original relative order of the odd values.
        while (!oddValues.isEmpty()) {
            q.enqueue(oddValues.pop());
        }

        return q;
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        // Replicating the exact example from the question.
        for (int value = 1; value <= 7; value++) {
            queue.enqueue(value);
        }

        reverseOdd(queue);
        queue.print();
    }
}
