/*
 * BINARY SEARCH TREE CLASS EXAMPLE
 *
 * Level-order array:
 * [70, 50, 90, 40, 60, 80, 95]
 *
 * Tree created by these values:
 *
 *                  70
 *                /    \
 *              50      90
 *             /  \    /  \
 *           40   60  80   95
 *
 * If the array positions start from 1, a node at position n has:
 * Left child position  = 2 * n
 * Right child position = 2 * n + 1
 *
 * Example: 50 is at position 2.
 * Its left child is at 2 * 2 = 4, which contains 40.
 * Its right child is at 2 * 2 + 1 = 5, which contains 60.
 *
 * Java arrays start from index 0, so a node at index i has:
 * Left child index  = 2 * i + 1
 * Right child index = 2 * i + 2
 *
 * Traversal results for this tree:
 * Pre-order:  70 50 40 60 90 80 95
 * In-order:   40 50 60 70 80 90 95
 * Post-order: 40 60 50 80 95 90 70
 *
 * In-order is important for a BST because it produces sorted values.
 * Therefore, 40 is the minimum, 50 is the second smallest,
 * and 60 is the third smallest. Reverse in-order produces the values
 * from largest to smallest and can find the nth largest value.
 *
 * The array formula explains the level-order representation above.
 * The insert function below builds the BST by comparing values.
 * Inserting this array from left to right creates the same tree.
 */
public class BinarySearchTree {
    TreeNode root;
    int inOrderNodeCount = 0;
    int nthSmallestValue = -1;
    int reverseInOrderNodeCount = 0;
    int nthLargestValue = -1;

    public TreeNode insert(TreeNode currentNode, int value) {
        /*
         * A binary search tree stores smaller values on the left
         * and larger values on the right.
         * The returned node reconnects the subtree after insertion.
         */
        // An empty position is the correct place for the new value.
        if (currentNode == null) {
            return new TreeNode(value);
        }

        if (value < currentNode.value) {
            currentNode.left = insert(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = insert(currentNode.right, value);
        }

        // Equal values are ignored, so duplicates are not inserted.
        return currentNode;
    }

    public boolean search(TreeNode currentNode, int value) {
        /*
         * Search uses the BST ordering to visit only one side of each node.
         * This is more efficient than searching both subtrees.
         */
        if (currentNode == null) {
            return false;
        }

        if (currentNode.value == value) {
            return true;
        }

        if (value < currentNode.value) {
            return search(currentNode.left, value);
        }

        return search(currentNode.right, value);
    }

    public int findMinimum(TreeNode currentNode) {
        /*
         * The minimum is the leftmost value because every left child
         * is smaller than its parent.
         */
        if (currentNode == null) {
            System.out.println("The tree is empty.");
            return -1;
        }

        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode.value;
    }

    public int findMaximum(TreeNode currentNode) {
        /*
         * The maximum is the rightmost value because every right child
         * is larger than its parent.
         */
        if (currentNode == null) {
            System.out.println("The tree is empty.");
            return -1;
        }

        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }

        return currentNode.value;
    }

    public void findNthSmallest(TreeNode currentNode, int position) {
        /*
         * In-order traversal visits BST values from smallest to largest.
         * We count each visited node. When the count reaches the requested
         * position, the current value is the nth smallest value.
         */
        if (currentNode == null || inOrderNodeCount >= position) {
            return;
        }

        findNthSmallest(currentNode.left, position);

        if (inOrderNodeCount >= position) {
            return;
        }

        inOrderNodeCount++;

        if (inOrderNodeCount == position) {
            nthSmallestValue = currentNode.value;
            return;
        }

        findNthSmallest(currentNode.right, position);
    }

    public void findNthLargest(TreeNode currentNode, int position) {
        /*
         * Reverse in-order traversal visits BST values from largest to smallest.
         * We count each visited node. When the count reaches the requested
         * position, the current value is the nth largest value.
         */
        if (currentNode == null || reverseInOrderNodeCount >= position) {
            return;
        }

        findNthLargest(currentNode.right, position);

        if (reverseInOrderNodeCount >= position) {
            return;
        }

        reverseInOrderNodeCount++;

        if (reverseInOrderNodeCount == position) {
            nthLargestValue = currentNode.value;
            return;
        }

        findNthLargest(currentNode.left, position);
    }

    public void preOrderTraversal(TreeNode currentNode) {
        /*
         * Pre-order visits nodes in this order:
         * Current node -> Left subtree -> Right subtree
         */
        if (currentNode == null) {
            return;
        }

        System.out.print(currentNode.value + " ");
        preOrderTraversal(currentNode.left);
        preOrderTraversal(currentNode.right);
    }

    public void inOrderTraversal(TreeNode currentNode) {
        /*
         * In-order traversal visits left, current, then right.
         * In a BST, this prints all values from smallest to largest.
         */
        if (currentNode == null) {
            return;
        }

        inOrderTraversal(currentNode.left);
        System.out.print(currentNode.value + " ");
        inOrderTraversal(currentNode.right);
    }

    public void postOrderTraversal(TreeNode currentNode) {
        /*
         * Post-order visits nodes in this order:
         * Left subtree -> Right subtree -> Current node
         */
        if (currentNode == null) {
            return;
        }

        postOrderTraversal(currentNode.left);
        postOrderTraversal(currentNode.right);
        System.out.print(currentNode.value + " ");
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] values = {70, 50, 90, 40, 60, 80, 95};

        // Insert each array value into its correct BST position.
        for (int value : values) {
            binarySearchTree.root = binarySearchTree.insert(
                    binarySearchTree.root,
                    value);
        }

        System.out.print("Pre-order traversal: ");
        binarySearchTree.preOrderTraversal(binarySearchTree.root);
        System.out.println();

        System.out.print("In-order traversal:  ");
        binarySearchTree.inOrderTraversal(binarySearchTree.root);
        System.out.println();

        System.out.print("Post-order traversal: ");
        binarySearchTree.postOrderTraversal(binarySearchTree.root);
        System.out.println();

        int requestedValue = 80;

        if (binarySearchTree.search(binarySearchTree.root, requestedValue)) {
            System.out.println(requestedValue + " exists in the tree.");
        } else {
            System.out.println(requestedValue + " does not exist in the tree.");
        }

        int missingValue = 100;

        if (binarySearchTree.search(binarySearchTree.root, missingValue)) {
            System.out.println(missingValue + " exists in the tree.");
        } else {
            System.out.println(missingValue + " does not exist in the tree.");
        }

        System.out.println("Minimum value: "
                + binarySearchTree.findMinimum(binarySearchTree.root));
        System.out.println("Maximum value: "
                + binarySearchTree.findMaximum(binarySearchTree.root));

        int smallestPosition = 2;
        binarySearchTree.inOrderNodeCount = 0;
        binarySearchTree.nthSmallestValue = -1;
        binarySearchTree.findNthSmallest(
                binarySearchTree.root,
                smallestPosition);
        System.out.println(smallestPosition + "nd smallest value: "
                + binarySearchTree.nthSmallestValue);

        smallestPosition = 3;
        binarySearchTree.inOrderNodeCount = 0;
        binarySearchTree.nthSmallestValue = -1;
        binarySearchTree.findNthSmallest(
                binarySearchTree.root,
                smallestPosition);
        System.out.println(smallestPosition + "rd smallest value: "
                + binarySearchTree.nthSmallestValue);

        int largestPosition = 2;
        binarySearchTree.reverseInOrderNodeCount = 0;
        binarySearchTree.nthLargestValue = -1;
        binarySearchTree.findNthLargest(
                binarySearchTree.root,
                largestPosition);
        System.out.println(largestPosition + "nd largest value: "
                + binarySearchTree.nthLargestValue);

        largestPosition = 3;
        binarySearchTree.reverseInOrderNodeCount = 0;
        binarySearchTree.nthLargestValue = -1;
        binarySearchTree.findNthLargest(
                binarySearchTree.root,
                largestPosition);
        System.out.println(largestPosition + "rd largest value: "
                + binarySearchTree.nthLargestValue);
    }
}
