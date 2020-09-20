学习笔记

本周主要学习了位运算、布隆过滤器、LRU缓存以及排序算法，虽然视频讲解内容比较简单，但实际做题及运用时还是会遇到一些困难，可能需要不断练习、运用和理解。位运算还不熟练，但大多能够理解，排序讲解比较清晰，也加固了之前这块学习的理解深度。

#### 经典初级排序算法
1. 冒泡排序
```
public int[] bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    return arr;
}
```

2. 选择排序
```
public int[] selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[i]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
    return arr;
}
```

3. 插入排序
```
public static int[] insertSort(int[] arr){
    int j, temp;
    for (int i = 1; i < arr.length; i++) {
        temp = arr[i];
        for(j = i - 1; j >= 0 && temp < arr[j]; j--){
            arr[j + 1] = arr[j];
        }
        arr[j + 1] = temp;
    }
    return arr;
}
```

4. 快速排序
```
// Method 1
public void quickSort(int[] arr, int start, int end){
    int pivot = arr[start], l = start, r = end;
    while (l < r) {
        while (l < r && arr[r] >= pivot) r--;
        arr[l] = arr[r];
        while (l < r && arr[l] <= pivot) l++;
        arr[r] = arr[l];
    }
    arr[r] = pivot;
    if (l - 1 > start) quickSort(arr, start, l - 1);
    if (r + 1 < end) quickSort(arr, r + 1, end);
}

// Method 2
public void quickSort2(int[] arr, int start, int end){
    if (end <= start) return;
    int pivot = partition(arr, start, end);
    quickSort2(arr, start, pivot - 1);
    quickSort2(arr, pivot + 1, end);
}

private int partition(int[] arr, int start, int end) {
    int pivot = end, count = start;
    for (int i = start; i < end; i++) {
        if (arr[i] < arr[pivot]) {
            int temp = arr[i]; arr[i] = arr[count]; arr[count] = temp;
            count++;
        }
    }
    int temp = arr[pivot]; arr[pivot] = arr[count]; arr[count] = temp;
    return count;
}
```

5. 归并排序
```
public void sort(int[] arr, int start, int end){
    if (end <= start) return;
    int mid = (start + end) >> 1;
    sort(arr, start, mid);
    sort(arr, mid + 1, end);
    merge(arr, start, mid, end);
}

private void merge(int[] arr, int start, int mid, int end){
    int[] temp = new int[end - start + 1];
    int l = start, r = mid + 1, i = 0;
    while (l <= mid && r <= end) {
        temp[i++] = arr[l] <= arr[r] ? arr[l++] : arr[r++];
    }
    while (l <= mid) temp[i++] = arr[l++];
    while (r <= end) temp[i++] = arr[r++];

    for (int j = 0; j < temp.length; j++) {
        arr[start + j] = temp[j];
    }
}
```

6. 堆排序
```
public void heapSort(int[] arr){
    int heapSize = arr.length;
    // build heap
    for (int i = heapSize / 2 - 1; i >= 0; i--) {
        heapify(arr, i, heapSize);
    }
    
    for (int i = heapSize - 1; i > 0; i--) {
        int temp = arr[i]; arr[i] = arr[0]; arr[0] = temp;
        heapSize--;
        heapify(arr, 0, heapSize);
    }
}

private void heapify(int[] arr, int i, int heapSize) {
    int left = 2 * i + 1, right = 2 * (i + 1), largest = i;
    if (left < heapSize && arr[left] > arr[largest]) largest = left;
    if (right < heapSize && arr[right] > arr[largest]) largest = right;
    if (largest != i) {
        int temp = arr[i]; arr[i] = arr[largest]; arr[largest] = temp;
        heapify(arr, largest, heapSize);
    }
}
```