## 문제
```
N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 
이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 
하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. 
N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.
```

## 풀이 방법

### 전제
- 모든 학생 X번 마을에 모여 파티
    - N개의 숫자로 구분된 마을에 한 명의 학생 존재
    - 마을 사이 M개의 단방향 도로
- 파티 참석
    - X번 마을까지 걸어감
    - N번 마을로 돌아옴
- 최단 시간에 오고 가는 길 지향
    - i번째 길을 지나는데 Ti의 시간 소비
    - 단방향 도로로 오고 가는 길 다를 수 있음
- 구할 값 : 가장 많은 시간 소비 학생 누구?

### 풀이 방법
- X에서 각 노드까지 가장 작은 time 값을 가진 노드로 움직이면서 DP에 저장 -> 다익스트라 알고리즘으로 이동!
- 모든 노드에서 X까지 움직인 값을 DP에 더함
- DP를 정렬한 후 가장 마지막 값 구함 = 가장 많은 시간
