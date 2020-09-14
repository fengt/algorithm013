学习笔记

本周主要学习了字典树、并查集、高级搜索、AVL树和红黑树。其中对字典树中路径压缩代码还有一些不理解，希望能结合示例详细讲解一下。并查集的代码模板感觉也非常巧妙，但是中间查找过程通过节点size进行平衡树的代码不太理解，也希望能再具体讲解一下。其中高级搜索也比较有意思，不过还未进行相应的题目训练，有待进一步加强和理解。AVL树和红黑树讲解比较巧妙，但未涉及相关题目的讲解和练习，希望能多讲些实际应用。总之，本周学习内容比较多，后面两节内容有待巩固和加强。


## 分析单词搜索2用Tire树方式实现的时间复杂度：O(M(4 * 3 ^ (L - 1)))
其中M是二维网格中的单元格数，L是单词的最大长度。

该算法循环遍历二维网格中的所有单元，因此在复杂度公式中我们有M作为因子。然后将其归结为每个启动单元所需的最大步骤数（即4 * 3 ^ (L - 1))。

假设单词的最大长度是L，从一个单元格开始，最初我们最多可以探索4个方向。假设每个方向都是有效的（即最坏情况），在接下来的探索中，我们最多有3个相邻的单元（不包括我们来的单元）要探索。因此，在回溯探索期间，我们最多遍历4 * 3 ^ (L - 1)个单元格。


## 总结双向BFS代码模版：

```
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) return 0;
    Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
    beginSet.add(beginWord);
    endSet.add(endWord);

    int res = 1;
    Set<String> visited = new HashSet<>();
    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
        if (beginSet.size() > endSet.size()) {
            Set<String> temp = beginSet;
            beginSet = endSet;
            endSet = temp;
        }

        Set<String> temp = new HashSet<>();
        for (String word : beginSet) {
            char[] chs = word.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char c = chs[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    if (c == j) continue;
                    chs[i] = j;
                    String target = String.valueOf(chs);

                    if (endSet.contains(target)) {
                        return res + 1;
                    }

                    if (!visited.contains(target) && wordSet.contains(target)) {
                        temp.add(target);
                        visited.add(target);
                    }
                }
                chs[i] = c;
            }
        }
        beginSet = temp;
        res++;
    }
    return 0;
}
```