public class RangeReverse {
    /*
     * Approach:
     * 1. Find the first and last nodes in the range.
     * 2. Disconnect the range. The remaining nodes stay connected.
     * 3. Reverse the disconnected range.
     * 4. Put the reversed range after the dummy head.
     *    Then connect its last node to the first remaining node.
     *
     * We only change links between existing nodes.
     * We do not create any new nodes.
     */
    static void rangeReverse(Node dHead, int start, int end) {
        // The linked list is empty
        if (dHead == null || dHead.next == null) {
            return;
        }

        // The given range is invalid
        if (start < 0 || end < start) {
            return;
        }

        // Step 1: Go to the node just before the start index
        Node nodeBeforeStart = dHead;
        int currentIndex = 0;

        while (currentIndex < start && nodeBeforeStart.next != null) {
            nodeBeforeStart = nodeBeforeStart.next;
            currentIndex++;
        }

        Node firstNodeInRange = nodeBeforeStart.next;

        // Start index does not exist in the linked list
        if (firstNodeInRange == null) {
            return;
        }

        // Move toward the end index
        // Stop early if there are no more nodes
        Node lastNodeInRange = firstNodeInRange;

        while (currentIndex < end && lastNodeInRange.next != null) {
            lastNodeInRange = lastNodeInRange.next;
            currentIndex++;
        }

        // Step 2: Disconnect the range from the remaining nodes
        Node nodeAfterEnd = lastNodeInRange.next;
        nodeBeforeStart.next = nodeAfterEnd;
        lastNodeInRange.next = null;

        // Step 3: Reverse the disconnected range
        Node previousNode = null;
        Node currentNode = firstNodeInRange;

        while (currentNode != null) {
            // Save the next node before changing the link
            Node nextNode = currentNode.next;

            // Point the current node backward
            currentNode.next = previousNode;

            // Move both references one step forward
            previousNode = currentNode;
            currentNode = nextNode;
        }

        // Step 4: Add the reversed range after the dummy head
        Node firstRemainingNode = dHead.next;
        dHead.next = previousNode;
        firstNodeInRange.next = firstRemainingNode;
    }
}
