import java.util.*;

public class BinaryTreeTraversalDemo{
	public static void main(String[] args) {
		
	}

	class State{
        int pos;
        TreeNode node;
        State(int _pos, TreeNode _node){
            pos = _pos;
            node = _node;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if(root == null)    return res;
        Stack<State> stack = new Stack();
        stack.push(new State(0, root));
        while(stack.isEmpty() == false){
            State state = stack.peek();
            switch(state.pos){
                case 0:
                    res.add(state.node.val);
                    state.pos++;
                    if(state.node.left != null)
                        stack.push(new State(0, state.node.left));
                    break;
                case 1:
                    state.pos++;
                    if(state.node.right != null)
                        stack.push(new State(0, state.node.right));
                    break;
                case 2:
                    stack.pop();
                    break;
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if(root == null)   return res;
        Stack<State> stack = new Stack();
        State state;
        stack.push(new State(0, root));
        while(stack.isEmpty() == false){
            state = stack.peek();
            switch(state.pos){
                case 0:
                    state.pos++;
                    if(state.node.left != null)
                        stack.push(new State(0, state.node.left));
                    break;
                case 1:
                    state.pos++;
                    res.add(state.node.val);
                    if(state.node.right != null)
                        stack.push(new State(0, state.node.right));
                    break;
                case 2:
                    stack.pop();
                    break;
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if(root == null)    return res;
        Stack<State> stack = new Stack();
        stack.push(new State(0, root));
        State state;
        while(stack.isEmpty() == false){
            state = stack.peek();
            switch(state.pos){
                case 0:
                    state.pos++;
                    if(state.node.left != null)
                        stack.push(new State(0, state.node.left));
                    break;
                case 1:
                    state.pos++;
                    if(state.node.right != null)
                        stack.push(new State(0, state.node.right));
                    break;
                case 2:
                    res.add(state.node.val);
                    stack.pop();
                    break;
            }
        }
        return res;
    }
}