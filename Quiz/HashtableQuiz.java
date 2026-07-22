class Node {
    int key;
    String val;
    Node next;

    Node(int key, String val) {
        this.key = key;
        this.val = val;
        this.next = null;
    }
}

public class HashtableQuiz {
    Node[] ht;

    public HashtableQuiz(int capacity) {
        ht = new Node[capacity];
    }

    public int hash(int key) {
        // According to the question, key 14 is mapped to index 1
        if (key == 14) {
            return 1;
        }

        return key % ht.length;
    }

    public void insert(int key, String value) {
        int index = hash(key);
        Node currentNode = ht[index];

        // First check whether the key already exists
        while (currentNode != null) {
            if (currentNode.key == key) {
                currentNode.val = value;
                return;
            }

            currentNode = currentNode.next;
        }

        Node newNode = new Node(key, value);

        // Case 1: Empty chain
        if (ht[index] == null) {
            ht[index] = newNode;
            return;
        }

        // Case 2: New key is odd
        // Odd-key nodes must remain at the end of the chain
        if (key % 2 != 0) {
            currentNode = ht[index];

            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = newNode;
            return;
        }

        // Case 3: New key is even

        // If the first node is odd, the new even node becomes the head
        if (ht[index].key % 2 != 0) {
            newNode.next = ht[index];
            ht[index] = newNode;
            return;
        }

        // Find the first odd node
        // Insert the new even node immediately before it
        currentNode = ht[index];

        while (currentNode.next != null &&
                currentNode.next.key % 2 == 0) {
            currentNode = currentNode.next;
        }

        newNode.next = currentNode.next;
        currentNode.next = newNode;
    }

    public void print() {
        // Iterate through every index of the Hashtable
        for (int i = 0; i < ht.length; i++) {
            System.out.print("Index " + i + ": ");
            Node currentNode = ht[i];

            // Print every node of the current chain
            while (currentNode != null) {
                System.out.print("(" + currentNode.key + ", " + currentNode.val + ") -> ");
                currentNode = currentNode.next;
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        HashtableQuiz hashtable = new HashtableQuiz(5);

        // Replicating the exact example from question 
        hashtable.ht[0] = new Node(10, "FISH");
        hashtable.ht[0].next = new Node(4, "EELS");
        hashtable.ht[0].next.next = new Node(5, "LION");

        hashtable.ht[1] = new Node(8, "DOG");
        hashtable.ht[1].next = new Node(3, "CAT");
        hashtable.ht[1].next.next = new Node(9, "CROW");

        hashtable.ht[3] = new Node(6, "OWL");
        hashtable.ht[3].next = new Node(11, "BEAR");

        hashtable.ht[4] = new Node(2, "WOLF");
        hashtable.ht[4].next = new Node(12, "GOAT");
        hashtable.ht[4].next.next = new Node(7, "DEER");

        // Insert 14 before the first odd-key node at index 1
        hashtable.insert(14, "TIGER");

        // Print the Hashtable after insertion
        hashtable.print();
    }
}
