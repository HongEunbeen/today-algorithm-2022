# 최단경로 알고리즘 - 플로이드-워셜

## 개념

플로이드-워셜 알고리즘은 가중치 그래프에서 모든 최단 경로를 구하는 알고리즘입니다.

한 번 실행하여 모든 노드 간 최단 경로를 구하는데 이때, 최단 경로를 찾기 위해 DP(동적 프로그래밍) 방식을 사용합니다.

🤔 다익스트라와의 차이점?

- 다익스트라 : 하나의 정점에서 다른 모든 정점까지의 최단 거리
    - 시작점으로 부터 나머지 정점까지 최단거리 구할 떄 사용
    - 음의 가중치 간선이 있으면 사용하지 못 함
- 프로이드-워셜 : 한 번 실행 해 모든 노드 간 최단 경로
    - 각 정점간 최단 경로 구할 떄 사용
    - 음의 가중치 간선에서 사용 가능
- 시작점으로부터 각 정점까지 최단 거리만 구할 때 → 다익스트라
- 간결한 소스코드, 간선 수가 많을 떄 → 플로이드-워셜

### [알고리즘 응용]

- 최단 경로 찾는 방향 그래프
- 실수 행렬의 반전 찾기
- 무방향 그래프가 이분법인지의 여부 테스트

## 알고리즘 과정

---

> 목표 : 모든 정점 사이의 최단 경로 구하기
> 

**1. 입력받은 그래프와 동일한 2차원 인접 행렬로 선언해 초기화**

![https://blog.kakaocdn.net/dn/bhGOct/btrF9gIgnFG/hAryZ5lDj6BsLpkNhuPOZk/img.png](https://blog.kakaocdn.net/dn/bhGOct/btrF9gIgnFG/hAryZ5lDj6BsLpkNhuPOZk/img.png)

해당 그래프를 5X5 2차원 인접 행렬로 변경해 i 정점에서 j 정점까지의 간선의 가중치를 입력합니다.

![https://blog.kakaocdn.net/dn/baNkpn/btrF76zp2Bp/u08W88Risxso8zSfaGusl1/img.png](https://blog.kakaocdn.net/dn/baNkpn/btrF76zp2Bp/u08W88Risxso8zSfaGusl1/img.png)

> 💡 INF = 해당 정점에서 특정 정점까지 길이 없음을 나타냅니다.
> 

**2. INF의 값을 채울 수 있도록 중간 정점 선택**

이제, INF의 값을 채울 수 있도록 정점들을 연결해 줘야 합니다. 간선으로 연결되어 있지 않은 정점들은 중간정점을 통해 정점 간의 가중치를 구할 수 있습니다.

![https://blog.kakaocdn.net/dn/bvj509/btrGaqQXeAL/MB9Xn40oTzexdWbrmaiU6k/img.png](https://blog.kakaocdn.net/dn/bvj509/btrGaqQXeAL/MB9Xn40oTzexdWbrmaiU6k/img.png)

위의 그래프는 1정점과 3정점이 연결되어 있지 않기 때문에 중간 정점(2정점, 4정점)을 선택해 1 → 중간정점 → 3 이렇게 연결합니다.

![https://blog.kakaocdn.net/dn/mK76l/btrF5aXbkPS/2KaEiWBDkoobauNksfVSo0/img.png](https://blog.kakaocdn.net/dn/mK76l/btrF5aXbkPS/2KaEiWBDkoobauNksfVSo0/img.png)

모든 정점들은 중간 정점으로 사용할 수 있기 때문에 총 5번(정점의 개수)의 계산이 수행되어야 합니다.

```
-  1 → 중간정점(2) → 3  = 4
-  1 → 중간정점(4) → 3  = 6
2정점을 중간정점으로 거치는 가중치의 값이 이 더 작으므로 가중치 4가 행렬에 들어감
```

이때, 위와 같이 중간정점이 여러개 일 경우에는 최단 거리를 구하는 알고리즘이기 때문에 행렬에 있는 값보다 계산된 값이 더 작으면 계산된 값으로 변경해줍니다.

**3. 모든 INF 값을 채우고 난 후 최단 거리**

![https://blog.kakaocdn.net/dn/dBXDaz/btrGaqQYpID/ZYkK77h4T3UofyppTnll91/img.png](https://blog.kakaocdn.net/dn/dBXDaz/btrGaqQYpID/ZYkK77h4T3UofyppTnll91/img.png)

5정점까지 중간정점으로 선정 후 계산을 마치면 행렬에는 모든 정점 간 최단 거리가 들어가게 됩니다.

## 시간 복잡도 및 빅오 표기

---

> O(n³)
> 

## 구현 with JAVA

```java
import java.util.*;
import java.lang.*;
import java.io.*;
 
class AllPairShortestPath
{
    final static int INF = 99999, V = 4;
 
    void floydWarshall(int graph[][])
    {
        int dist[][] = new int[V][V];
        int i, j, k;

        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

        for (k = 0; k < V; k++)
        {
            for (i = 0; i < V; i++)
            {
                for (j = 0; j < V; j++)
                {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
 
    public static void main (String[] args)
    {
        int graph[][] = { {0,   5,  INF, 10},
                          {INF, 0,   3, INF},
                          {INF, INF, 0,   1},
                          {INF, INF, INF, 0}
                        };

        AllPairShortestPath a = new AllPairShortestPath();
 
        a.floydWarshall(graph);
    }
}
```

## 출처

- [https://www.programiz.com/dsa/floyd-warshall-algorithm](https://www.programiz.com/dsa/floyd-warshall-algorithm)
- [https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/](https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/)
- [https://codedoc.tistory.com/95](https://codedoc.tistory.com/95)
