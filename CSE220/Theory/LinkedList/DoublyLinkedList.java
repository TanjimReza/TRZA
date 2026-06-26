class DoublyNode {
    int value;
    DoublyNode next;
    DoublyNode prev;

    DoublyNode(int value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

public class DoublyLinkedList {
    DoublyNode head;
    DoublyNode tail;

    public void createFromArray(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Invalid array. Cannot create linked list.");
            return;
        }

        // First item will always be the head of the DLL
        head = new DoublyNode(array[0]);
        // tail is the last item in the list, initially head = tail
        tail = head;

        DoublyNode currentNode = head;

        for (int i = 1; i < array.length; i++) {
            DoublyNode newNode = new DoublyNode(array[i]);

            currentNode.next = newNode;
            newNode.prev = currentNode;

            currentNode = currentNode.next;
            tail = currentNode;
        }
    }

    public void iterate() {
        /*
         * We use currentNode for traversal.
         * The head reference should not be modified,
         * otherwise we would lose access to the beginning
         * of the linked list.
         */
        DoublyNode currentNode = head;

        while (currentNode != null) {
            System.out.print(currentNode.value + " <-> ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public int count() {
        int count = 0;
        DoublyNode currentNode = head;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }

        return count;
    }

    public DoublyNode nodeAt(int index) {
        int currentIndex = 0;
        DoublyNode currentNode = head;

        while (currentNode != null) {
            if (currentIndex == index) {
                return currentNode;
            }
            currentIndex++;
            currentNode = currentNode.next;
        }

        return null;
    }

    public void prepend(int value) {
        /*
         * Add a new node before the current head.
         * The new node becomes the new head.
         */
        DoublyNode newHead = new DoublyNode(value);

        if (head == null) {
            head = newHead;
            tail = newHead;
            return;
        }

        newHead.next = head;
        head.prev = newHead;
        head = newHead;
    }

    public void append(int value) {
        /*
         * We have a tail pointer that is pointing the end of the list
         * So we can append the new item in O(1)
         */
        DoublyNode newNode = new DoublyNode(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void insertAt(int index, int value) {
        /*
         * Insert an item in any particular index.
         * If index = 0, then insert at head.
         * If index = count(), then insert at tail.
         */
        if (index < 0 || index > count()) {
            System.out.println("Index out of bound!");
            return;
        }

        if (index == 0) {
            prepend(value);
            return;
        }

        if (index == count()) {
            append(value);
            return;
        }

        DoublyNode newNode = new DoublyNode(value);
        DoublyNode currentNode = nodeAt(index);
        DoublyNode previousNode = currentNode.prev;

        previousNode.next = newNode;
        newNode.prev = previousNode;

        newNode.next = currentNode;
        currentNode.prev = newNode;
    }

    public void deleteAt(int index) {
        /*
         * Delete an item from any particular index.
         * If index = 0, then delete head.
         * If index = count() - 1, then delete tail.
         */
        if (index < 0 || index >= count()) {
            System.out.println("Index out of bound!");
            return;
        }

        DoublyNode currentNode = nodeAt(index);

        if (currentNode == head && currentNode == tail) {
            head = null;
            tail = null;
            return;
        }

        if (currentNode == head) {
            head = head.next;
            head.prev = null;
            return;
        }

        if (currentNode == tail) {
            tail = tail.prev;
            tail.next = null;
            return;
        }

        DoublyNode previousNode = currentNode.prev;
        DoublyNode nextNode = currentNode.next;

        previousNode.next = nextNode;
        nextNode.prev = previousNode;
    }

    public void deleteValue(int value) {
        /*
         * Delete the first node that contains the given value.
         */
        DoublyNode currentNode = head;

        while (currentNode != null) {
            if (currentNode.value == value) {
                if (currentNode == head && currentNode == tail) {
                    head = null;
                    tail = null;
                    return;
                }

                if (currentNode == head) {
                    head = head.next;
                    head.prev = null;
                    return;
                }

                if (currentNode == tail) {
                    tail = tail.prev;
                    tail.next = null;
                    return;
                }

                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
                return;
            }

            currentNode = currentNode.next;
        }

        System.out.println("Value not found!");
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        // Create from array
        int[] array = { 10, 20, 30, 40 };
        list.createFromArray(array);

        // Iterate
        System.out.println("Original list:");
        list.iterate();

        // Insert
        list.insertAt(2, 25);
        System.out.println("After inserting 25 at index 2:");
        list.iterate();

        // Delete by index
        list.deleteAt(3);
        System.out.println("After deleting index 3:");
        list.iterate();

        // Delete by value
        list.deleteValue(10);
        System.out.println("After deleting value 10:");
        list.iterate();
    }

}