## 문제
```
강호는 N개의 도시로 이루어진 나라에 살고 있다. 각 도시는 M개의 도로로 연결되어 있으며, 각 도로를 지날 때 필요한 시간이 존재한다. 도로는 잘 연결되어 있기 때문에, 도시 A에서 B로 이동할 수 없는 경우는 존재하지 않는다.
도시 A에서 도시 B로 바로 갈 수 있는 도로가 있거나, 다른 도시를 거쳐서 갈 수 있을 때, 도시 A에서 B를 갈 수 있다고 한다.
강호는 모든 쌍의 도시에 대해서 최소 이동 시간을 구해놓았다. 민호는 이 표를 보고 원래 도로가 몇 개 있는지를 구해보려고 한다.
예를 들어, 예제의 경우에 모든 도시 사이에 강호가 구한 값을 가지는 도로가 존재한다고 해도 된다. 하지만, 이 도로의 개수는 최솟값이 아니다. 예를 들어, 도시 1-2, 2-3, 1-4, 3-4, 4-5, 3-5를 연결하는 도로만 있다고 가정해도, 강호가 구한 모든 쌍의 최솟값을 구할 수 있다. 이 경우 도로의 개수는 6개이고, 모든 도로의 시간의 합은 55이다.
모든 쌍의 도시 사이의 최소 이동 시간이 주어졌을 때, 이 나라에 존재할 수 있는 도로의 개수의 최솟값일 때, 모든 도로의 시간의 합을 구하는 프로그램을 작성하시오.
```

## 풀이 방법
- 플로이드-워셜 알고리즘으로 최단경로가 구해진 2차인접행렬이 입력임
- 입력값 가지고 플로이드-워셜 전 값을 구해야 함
  - 플로이드-워셜 돌리면서 조건문만 반대로
  - for문 돌릴 떄 조건문 안 들어가면 입력 자체가 문제 = return -1
- 출력할 떄는 모든 값을 다 더한 값 / 2로 출력 (동일한 경로 `[i][j]` = `[j][i]`는 제외해야함)
  - 다른 분들 코드보면 check 배열로 동일한 경로는 안 돌아가게 할 수도 있을듯 

골드4 문제였는데 생각보다 쉬웠음..!  