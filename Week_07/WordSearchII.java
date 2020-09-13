package org.fengt.leetcode.frequency.num_212;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    /**
     * Trie & BackTracking
     * Time complexity: O(M * N * 4 * (3 ^ (L - 1))) L is word's average length
     * Space complexity: O(N) N is all letters in words
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;

        p = p.next[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null;
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    private void dfsWithPruning(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;

        TrieNode currNode = p.next[c - 'a'];
        if (currNode.word != null) {
            res.add(currNode.word);
            currNode.word = null;
        }

        board[i][j] = '#';
        if (i > 0) dfsWithPruning(board, i - 1, j, currNode, res);
        if (j > 0) dfsWithPruning(board, i, j - 1, currNode, res);
        if (i < board.length - 1) dfsWithPruning(board, i + 1, j, currNode, res);
        if (j < board[0].length - 1) dfsWithPruning(board, i, j + 1, currNode, res);
        board[i][j] = c;

        // pruning
        if (currNode.next == null) {
            p.next[c - 'a'] = null;
        }
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }


    class TrieNode {
        private TrieNode[] next = new TrieNode[26];
        private String word;
    }
}
