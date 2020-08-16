package org.fengt.leetcode.frequency.num_236;

import org.core1.design.composite.Tree;
import org.fengt.leetcode.structure.TreeNode;

import java.util.*;

public class LowestCommonAncestorOfABinaryTree {

    /**
     * Approach 1: Recursive
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * Approach 2: Iterative using parent pointers
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        // Stack for tree traversal
        Queue<TreeNode> queue = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        queue.add(root);
        parent.put(root, null);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                queue.add(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.add(node.right);
            }
        }

        Set<TreeNode> ans = new HashSet<>();
        while (p != null) {
            ans.add(p);
            p = parent.get(p);
        }

        while (!ans.contains(q)) q = parent.get(q);

        return q;
    }
}
