# 이진 탐색 (Binary Search)

## 개념

이진 검색 알고리즘은 오름차순으로 정렬된 리스트에서 특정한 값의 위치를 찾는 알고리즘입니다.

장점 : 검색이 반복될 때마다 목표값을 찾을 확률이 두배가 되므로 속도가 빠릅니다.

단점 : 검색 원리상(중간 값을 찾아야 하기에) 정렬된 리스트에만 사용할 수 있습니다.

이진 검색 알고리즘의 동작 방식은 다음과 같습니다.

1. 배열의 중간 값을 임의의 값으로 선택
2. 중앙 값과 찾고자 하는 값의 크고 작음을 비교
    - 중앙 값 = 찾는 값 : 값을 찾았으니 검색 종료
    - 중앙값 > 찾는 값 : 중앙값 기준 배열의 왼쪽 구간을 대상으로 탐색
    - 중앙값 < 찾는 값 : 중앙값 기준 배열의 오른쪽 구간을 대상으로 탐색
3. 값을 찾거나 간격이 0이 될때까지 반복

### 알고리즘 설명

**선행 조건**

- 배열 : arr[1,2,3,4,5,6,7]
- 찾는 값 : 5

![Untitled](https://github.com/HongEunbeen/Today_Algorithm_2022/blob/main/docs/binary_search/images/image_1.png)

1. **배열의 중간 값을 임의의 값으로 선택**
    
    ```
    key(찾는 값) : 5
    mid(중앙 값) : 4
    검색 범위 : 1, 2, 3, 4, 5, 6, 7
    ```
    
    
  ![Untitled](https://github.com/HongEunbeen/Today_Algorithm_2022/blob/main/docs/binary_search/images/image_2.png)
    
2. **중앙 값과 찾고자 하는 값의 크고 작음을 비교**
    - 중앙값(4) < 찾는 값(5) : 중앙값 기준 배열의 오른쪽 구간을 대상으로 탐색
    (값을 찾거나 간격이 0이 될때까지 반복)
        
        ```
        key(찾는 값) : 5
        mid(중앙 값) : 6
        검색 범위 : 4, 5, 6, 7 
        ```
       
      ![Untitled](https://github.com/HongEunbeen/Today_Algorithm_2022/blob/main/docs/binary_search/images/image_3.png)
        
    - 중앙값(6) > 찾는 값(5) : 중앙값 기준 배열의 왼쪽 구간을 대상으로 탐색
    (값을 찾거나 간격이 0이 될때까지 반복)
        
        ```
        key(찾는 값) : 5
        mid(중앙 값) : 5
        검색 범위 : 4, 5, 6
        ```
        
        
      ![Untitled](https://github.com/HongEunbeen/Today_Algorithm_2022/blob/main/docs/binary_search/images/image_4.png)
        
    - 중앙 값 = 찾는 값 : 값을 찾았으니 검색 종료
3. **값을 찾거나 간격이 0이 될때까지 반복**

**💡 TIP. 중앙 값 계산식**

```java
int mid = (low + high) / 2
```

### 알고리즘 종료 조건

- 리스트에서 검색할 값과 같은 요소 발견한 경우(검색 성공)
- 검색할 범위가 더 이상 없을 경우(검색 실패)

## 시간 복잡도 및 빅오 표기

| Operation | BEST | AVE | WORST |
| :---: | :----: | :----: | :----: |
| Search | Θ(1) | Θ(log n) | Θ(log n) |

- n = 데이터 수

## 이진 탐색 알고리즘 구현 with JAVA

```java
public int binarySearch(int[] arr, int target) {
    int start = 0;
    int end = arr.length - 1;
    int mid = 0;

    while (start <= end) {
        mid = (start + end) / 2;
        if (target == arr[mid]) {
            return mid;
        }else if (arr[mid] < target) {
            start = mid + 1;
        }else if (target < arr[mid]) {
            end = mid - 1;
        }
    }
    throw new NoSuchElementException("can't find target.");
}
```

## 출처

- [https://ko.wikipedia.org/wiki/이진_검색_알고리즘](https://ko.wikipedia.org/wiki/%EC%9D%B4%EC%A7%84_%EA%B2%80%EC%83%89_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
- [https://yoongrammer.tistory.com/75](https://yoongrammer.tistory.com/75)
