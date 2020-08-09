import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    /**
     * Approach 1: Recursive
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        traverse(root, output);
        return output;
    }

    private void traverse(TreeNode root, List<Integer> output) {
        if (root != null) {
            output.add(root.val);
            traverse(root.left, output);
            traverse(root.right, output);
        }
    }

    /**
     * Approach 2: Iterative
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null) return output;
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return output;
    }
}
