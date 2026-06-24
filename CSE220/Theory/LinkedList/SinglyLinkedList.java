class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}

public class SinglyLinkedList {
    Node head;
    Node tail;

    public void iterate() {
        /*
         * We use currentNode for traversal.
         * The head reference should not be modified,
         * otherwise we would lose access to the beginning
         * of the linked list.
         */
        Node currentNode = head;

        // We can iterate until currentNode is null,
        // When it's null it's ensured that we have reached the end
        while (currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public void createFromArray(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Invalid array. Cannot create linked list.");
            return;
        }

        // First item will always be the head of the LL
        head = new Node(array[0]);
        // tail is the last item in the list, initially head = tail
        tail = head;
        // For every other item, we iterate and set
        Node currentNode = head;
        for (int i = 1; i < array.length; i++) {
            Node newNode = new Node(array[i]);
            currentNode.next = newNode;
            currentNode = currentNode.next;
            tail = currentNode;
        }

    }

    public int count() {
        int count = 0;
        Node currentNode = head;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }

        return count;
    }

    public void append(int value) {
        /*
         * We need to iterate the linked list till end
         * Then we can add one item in the end
         * We iterate the whole list therefore O(n)
         */
        Node currentNode = head;

        while (currentNode.next != null) {
            /*
             * We check if current node's next item is null here,
             * that way we can go till the last item, instead of going to null
             */
            currentNode = currentNode.next;
        }

        // We are at the last item now
        Node newNode = new Node(value);
        currentNode.next = newNode;
        tail = currentNode.next;
    }

    public void efficientAppend(int value) {
        /*
         * We have a tail pointer that is pointing the end of the list
         * So we can append the new item in the tail and call the new item tail
         * This way we can append in O(1)
         */
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = tail.next;
    }

    public void prepend(int value) {
        /*
         * Add a new node before the current head.
         * The new node becomes the new head.
         */
        Node newHead = new Node(value);

        if (head == null) {
            /*
             * If there is no item in the list
             * then, this is both the head & tail
             */
            head = newHead;
            tail = newHead;
            return;
        }

        // Head is now after the newly created node
        newHead.next = head;
        // Update head to point to the new head
        head = newHead;
    }

    public Node nodeAt(int index) {
        /*
         * We find any value of a particular index in the list
         * If index = 0: Head is the first item
         * Else we iterate till that index
         */
        int currentIndex = 0;
        Node currentNode = head;

        if (index == 0) {
            return currentNode;
        }

        // We can iterate till the end of the list
        while (currentNode != null) {
            if (currentIndex == index) {
                return currentNode;
            }
            currentIndex++;
            currentNode = currentNode.next;
        }
        return null;
    }

    public boolean search(int value) {
        /*
         * Searches for a value in the list
         */
        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.value == value) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public int indexOf(int value) {
        /*
         * Get index of an element
         * If element is not present then return -1
         */

        int currentIndex = 0;
        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.value == value) {
                return currentIndex;
            }
            currentIndex++;
            currentNode = currentNode.next;
        }

        return -1;

    }

    public void insertAt(int index, int value) {
        /*
         * Insert an item in any particular index
         * If index=0, then insert at head
         * Else we go till intened index and add after that
         */

        if (index < 0 || index > count()) {
            System.out.println("Index out of bound!");
            return;
        }
        if (index == 0) {
            // This will become a new head
            prepend(value);
            return;
        }

        Node newNode = new Node(value);
        Node previousNode = nodeAt(index - 1);
        if (previousNode != null) {
            newNode.next = previousNode.next;
            previousNode.next = newNode;
        }

        // Updating tail, if appended as last item
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    public void rotateLeft() {
        if (head == null || head.next == null) {
            return;
        }
        Node oldHead = head;
        head = head.next;
        tail.next = oldHead;
        oldHead.next = null;
        tail = oldHead;
    }

    public void rotateLeft(int k) {

        int size = count();
        k = k % size;

        if (k == 0) {
            return;
        }
        for (int i = 0; i < k; i++) {
            rotateLeft();
        }
    }

    public void optimizedRotateLeft(int k) {

        if (head == null || head.next == null || k <= 0) {
            return;
        }

        k = k % count();
        if (k == 0) {
            return;
        }

        Node newTail = head;
        for (int i = 1; i < k; i++) {
            newTail = newTail.next;
        }

        tail.next = head;
        head = newTail.next;
        newTail.next = null;
        tail = newTail;
    }

    public static void main(String[] args) {

        // Node firstNode = new Node(0);
        // System.out.println("Node: " + firstNode + " & Value: " + firstNode.value);
        // Node secondNode = new Node(5);
        // System.out.println("Node: " + secondNode + " & Value: " + secondNode.value);

        /* Creating a Singly Linked List */
        // SinglyLinkedList list = new SinglyLinkedList();

        /* Iterating the linked list */
        // list.head = new Node(5);
        // list.iterate();
        // list.count();

        SinglyLinkedList linkedList = new SinglyLinkedList();
        int[] myArray = { 10, 20, 30, 40 };

        // Creating a linked list from an Array
        linkedList.createFromArray(myArray);
        System.out.print("Iterating through the list: ");
        linkedList.iterate();
        int node_count = linkedList.count();
        System.out.println("Nodes in linked list: " + node_count);

        // Appending a new item in the list
        int newItem = 50;
        System.out.print("Appending `" + newItem + "` in the list: ");
        linkedList.append(newItem);
        linkedList.iterate();

        // Since we have a tail, we can do this efficiently
        int anotherNewItem = 100;
        System.out.print("Efficiently Appending `" + anotherNewItem + "` in the list: ");
        linkedList.efficientAppend(anotherNewItem);
        linkedList.iterate();

        int newHeadItem = 0;
        System.out.print("Prepending `" + newHeadItem + "` in the list: ");
        linkedList.prepend(newHeadItem);
        linkedList.iterate();

        int nodeIndex = 2;
        System.out.print("Getting Node at index `" + nodeIndex + "`: ");
        Node node_at_index = linkedList.nodeAt(nodeIndex);
        System.out.println(node_at_index + ", value: " + node_at_index.value);

        int searchValue = 30;
        System.out.print("Searching `" + searchValue + "` in the list:  ");
        System.out.println(linkedList.search(searchValue) ? "Found!" : "Not Found!");

        int indexOfValue = 40;
        System.out.print("Finding index of `" + indexOfValue + "` in the list: ");
        System.out.println(linkedList.indexOf(indexOfValue));

        int[] indexes = { 0, 2, 9 };
        int[] values = { 15, 25, 95 };

        System.out.print("Before insertion: ");
        linkedList.iterate();
        for (int i = 0; i < indexes.length; i++) {
            System.out.print("Inserting " + values[i] + " at " + indexes[i] + " index : ");
            linkedList.insertAt(indexes[i], values[i]);
            linkedList.iterate();
        }

        linkedList.rotateLeft();
        linkedList.iterate();

        linkedList.rotateLeft(3);
        linkedList.iterate();

        linkedList.optimizedRotateLeft(2);
        linkedList.iterate();
    }
}
