
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BST{
    TreeNode root;

    public TreeNode createFromArray(int[] array){

        if (array == null || array.length == 0){
            return null;
        }

        for (int i = 0; i < array.length; i++){
            root = insertNode(root, array[i]);
        }

        return root;
    }

    public TreeNode insertNode(TreeNode root, int value){
        
        if (root == null){
            TreeNode node = new TreeNode(value);
            return node;
        }

        if (value < root.value){
            root.left = insertNode(root.left, value);
        }

        else if(value > root.value){
            root.right = insertNode(root.right, value);
        }

        return root;
    }
    

    public void inOrderTraverse(TreeNode root){
        if (root == null){
            return;
        }
        
        inOrderTraverse(root.left);
        System.out.print(root.value + " ");
        inOrderTraverse(root.right);

    }


    public int height(TreeNode root){
        if (root == null){
            return -1;
        }

        return 1 + Math.max(
            height(root.left), height(root.right)
        );
    }

    public boolean isBalanced(TreeNode root){
        if (root == null){
            return true;
        }

        return  Math.abs(height(root.left) - height(root.right)) <=1
                && isBalanced(root.left)
                && isBalanced(root.right);
        
    }

    public TreeNode createBalancedFromArray(int[] array, int low, int high){

        if (array == null || low > high){
            return null;
        }

        int mid = low + (high - low) / 2;
        TreeNode node = new TreeNode(array[mid]);

        node.left = createBalancedFromArray(array, low, mid - 1);
        node.right = createBalancedFromArray(array, mid + 1, high);

        return node;
    }

    public static void main(String[] args){
        int[] array = {20,40,50,60,55,70,90,80,75,85,95,99};
        // int[] array = {10,20,30,40,50};

        BST mytree = new BST();

        TreeNode root = mytree.createFromArray(array);
        // mytree.inOrderTraverse(mytree.root);
        int tree_height = mytree.height(mytree.root);
        System.out.println(tree_height);
        System.out.println(mytree.isBalanced(mytree.root));


        // int[] array = {10,20,30,40,50};
        mytree.createBalancedFromArray(array, 0, array.length-1);
        System.out.println(mytree.height(mytree.root));
        System.out.println(mytree.isBalanced(mytree.root));


    }
}
