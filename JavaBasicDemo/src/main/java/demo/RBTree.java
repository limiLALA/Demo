 package demo;

public class RBTree {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        int color;//0 b 1 r

        public TreeNode(int val) {
            this.val = val;
            this.color = 0;//b as default
        }
    }

    TreeNode root;

    int add(TreeNode node){
        if (root == null){
            root = node;
            return 0;

        }

        TreeNode root = this.root;



        return 0;
    }

}
