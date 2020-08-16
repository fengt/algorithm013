学习笔记

本周主要学习了递归、迭代以及分治和回溯，视频讲解比较简洁，但在做相关题目时比较难以理解（回溯）。同时，做题过程中需要注意一些点：a) 不要人肉递归（最大误区）。b) 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）。c) 数学归纳思维。

#### 1. 递归代码模板

```
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
}
```

#### 2. 分治代码模板

```
private static int divide_conquer(Problem problem, ) {
  
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  subProblems = split_problem(problem)
  
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  
  result = process_result(res0, res1);
  
  return result;
}
```