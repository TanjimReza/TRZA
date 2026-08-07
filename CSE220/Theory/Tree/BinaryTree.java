class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    TreeNode root;

    public void createFromArray(Integer[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Invalid array. Cannot create binary tree.");
            return;
        }

        /*
         * We treat the array as a level-order representation of the tree.
         * Because Java arrays start from index 0, a node at index i has:
         * Left child index  = 2 * i + 1
         * Right child index = 2 * i + 2
         *
         * We use null when a position does not contain a node.
         * The array used in main creates this tree from the PDF:
         *
         *              70
         *            /    \
         *          50      90
         *         /  \    /  \
         *       40   60  80   95
         *      /        /  \    \
         *     20       75  85    99
         */
        root = createNodeFromArray(array, 0);
    }

    private TreeNode createNodeFromArray(Integer[] array, int i) {
        // Going beyond the array or finding null means there is no node here.
        if (i >= array.length || array[i] == null) {
            return null;
        }

        TreeNode currentNode = new TreeNode(array[i]);

        // Create the left and right subtrees using their array positions.
        currentNode.left = createNodeFromArray(array, 2 * i + 1);
        currentNode.right = createNodeFromArray(array, 2 * i + 2);

        return currentNode;
    }

    public void preOrderTraversal(TreeNode currentNode) {
        /*
         * Pre-order visits nodes in this order:
         * Current node -> Left subtree -> Right subtree
         */
        // A null node has no value or children to visit.
        if (currentNode == null) {
            return;
        }

        System.out.print(currentNode.value + " ");
        preOrderTraversal(currentNode.left);
        preOrderTraversal(currentNode.right);
    }

    public void inOrderTraversal(TreeNode currentNode) {
        /*
         * In-order visits nodes in this order:
         * Left subtree -> Current node -> Right subtree
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

    public int calculateHeight(TreeNode currentNode) {
        /*
         * Height is the number of edges on the longest path from
         * the current node to a leaf.
         * Therefore, a leaf has height 0 and an empty tree has height -1.
         */
        if (currentNode == null) {
            return -1;
        }

        return Math.max(
                calculateHeight(currentNode.left),
                calculateHeight(currentNode.right)) + 1;
    }

    public int calculateDepth(TreeNode currentNode, int value, int currentDepth) {
        /*
         * Depth is the number of edges from the root to the requested node.
         * The root has depth 0.
         * If duplicate values exist, this returns the depth of the first match.
         */
        if (currentNode == null) {
            return -1;
        }

        if (currentNode.value == value) {
            return currentDepth;
        }

        int leftDepth = calculateDepth(
                currentNode.left,
                value,
                currentDepth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        }

        return calculateDepth(
                currentNode.right,
                value,
                currentDepth + 1);
    }

    public int calculateLevel(TreeNode currentNode, int value) {
        /*
         * The level of a node is the same as its depth.
         * Root: depth 0, level 0
         * Root's children: depth 1, level 1
         */
        return calculateDepth(currentNode, value, 0);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Integer[] treeValues = {
                70, 50, 90, 40, 60, 80, 95, 20,
                null, null, null, 75, 85, null, 99
        };

        // Create the same tree used by the traversal example in the PDF.
        binaryTree.createFromArray(treeValues);

        System.out.print("Pre-order traversal: ");
        binaryTree.preOrderTraversal(binaryTree.root);
        System.out.println();

        System.out.print("In-order traversal: ");
        binaryTree.inOrderTraversal(binaryTree.root);
        System.out.println();

        System.out.print("Post-order traversal: ");
        binaryTree.postOrderTraversal(binaryTree.root);
        System.out.println();

        System.out.println("Height of the tree: " + binaryTree.calculateHeight(binaryTree.root));

        int requestedValue = 85;
        int requestedValueDepth = binaryTree.calculateDepth(binaryTree.root, requestedValue, 0);

        if (requestedValueDepth == -1) {
            System.out.println("Value " + requestedValue + " does not exist in the tree.");
        } else {
            System.out.println("Depth of " + requestedValue + ": " + requestedValueDepth);
            System.out.println("Level of " + requestedValue + ": "
                    + binaryTree.calculateLevel(binaryTree.root, requestedValue));
        }
    }
}
