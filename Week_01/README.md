学习笔记

本周主要学习了数组、链表、跳表、栈、队列以及学习过程中的一些方法，如切题四件套、无毒神掌等。刷题过程中体会到无毒神掌方法的重要性，并按照该方法进行练习取得了一定的进步，对涉及知识点的leetcode题有了一些掌握，但仍需要加强练习。

### 1. 用 add first 或 add last 这套新的 API 改写 Deque 的代码：

```
public void test() {
    Deque<String> deque = new LinkedList<>();
    deque.addFirst("a");
    deque.addLast("b");
    deque.offerFirst("c");
    System.out.println(deque);

    String str = deque.peekLast();
    System.out.println(str);
    System.out.println(deque);

    while (deque.size() > 0) {
        System.out.println(deque.pollLast());
    }
    System.out.println(deque.pollFirst());
    System.out.println(deque);
}
```

### 2. 分析 Queue 和 Priority Queue 的源码：

(1) Queue是一个接口，定义了队列的基本操作：
```
public interface Queue<E> extends Collection<E> {

    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E element();

    E peek();
}
```

(2) Priority Queue是Queue的实现类，一个优先级队列。
- PriorityQueue是一种无界的，线程不安全的队列。
- PriorityQueue是基于数组实现的平衡二叉堆，节点queue[n]的两个孩子节点从左到右分别是queue[2n+1]和queue[2(n+1)]。
- PriorityQueue存储的元素要求必须是可比较的对象， 如果不是就必须明确指定比较器。
- 初始化容量为11, 每一个父节点都比子节点小，根结点queue[0]为最小值。

