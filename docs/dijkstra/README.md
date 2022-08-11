# 최단경로 알고리즘 - 다익스트라

## 개념

에츠허르 다익스트라가 고안한 알고리즘으로, 그래프의 한 정점에서 모든 정점까지의 최단거리를 각각 구하는 알고리즘입니다.

- 그래프 방향의 유무는 상관 없습니다.
- 간선의 가중치 중 음수가 존재한다면 사용할 수 없습니다.
- 우선순위 큐 등을 이용하면 시간 복잡도를 개선할 수 있습니다.

🤔 플로이드-워셜 알고리즘과 차이점

- 다익스트라 알고리즘 : 하나의 정점에서부터 최단경로를 구하는 알고리즘
- 플로이드-워셜 알고리즘 : 가능한 모든 정점쌍들에 대한 최단 거리 구하는 알고리즘

즉, 모든 정점에 대해 다익스트라 알고리즘을 실행한다면 플로이드-워셜 알고리즘과 똑같은 결과가 도출됩니다.

### [ 알고리즘 활용 ]

- 내비게이션에서 지도 상 도시 사이의 빠른 길 찾기
- 미로탐색 알고리즘
- 최소 운송비 지점 구하기

## 알고리즘 설명

> 목표 : 하나의 정점에서부터 N까지의 최단경로를 구하기
>
### [ 선행조건 ]

1. 가중치가 양수인 그래프에서만 작동할 수 있습니다. 
    
    ![1](https://user-images.githubusercontent.com/37995685/184047548-8238b929-f8a9-4f94-9b1b-0871932e3db2.png)
    
2. 확인되지 않는 거리는 모두 INFINITE 값으로 초기화 해 선언합니다.
    
    ![2](https://user-images.githubusercontent.com/37995685/184047512-c037cded-d59e-479b-8260-1400b3cdf651.png)
    
    > 💡 해당 배열은 시작 정점부터 해당 정점까지의 거리를 저장합니다.
    > 
3. 다음에 탐색할 정점은 Queue를 이용해 관리합니다.
    
    ![3](https://user-images.githubusercontent.com/37995685/184047513-83a1da0b-1148-47f6-912a-09191ea8c0a1.png)
    
### [ 알고리즘 동작 ]

1. 시작 정점의 가중치를 0으로 할당해 알고리즘을 실행합니다.
    
    ![4](https://user-images.githubusercontent.com/37995685/184047515-9087f834-f64e-4722-8ff5-22f51ddadf92.png)
    
2. 해당 정점에 연결된 간선의 가중치와 정점까지의 최소 거리를 비교 후 최소값을 거리 값으로 업데이트 합니다.
    
    ![5](https://user-images.githubusercontent.com/37995685/184047517-19cd956b-6ffe-43e7-9ff3-3fb19ea46601.png)
    
    - `A-B 가중치(4)` < `B까지의 거리(INF)` = 업데이트 `B(INF → 4)`
    - `A-C 가중치(5)` < `C까지의 거리(INF)` = 업데이트 `C(INF → 5)`
3. Queue에 저장된 다음 정점으로 시작 정점을 변경 후 위 과정을 반복합니다.
    
    ![6](https://user-images.githubusercontent.com/37995685/184047518-56806302-2a23-4841-81ff-2ee5ed0dd8a0.png)
    
    - `B까지의 거리(4) + B-C 가중치(11)`  > `C까지의 거리(5)` = 유지 `C(5 → 5)`
    - `B까지의 거리(4) + B-D 가중치(9)` < `D까지의 거리(INF)` = 업데이트 `D(INF → 13)`
    - `B까지의 거리(4) + B-E 가중치(3)` < `E까지의 거리(INF)`= 업데이트 `E(INF → 11)`
    
    ![7](https://user-images.githubusercontent.com/37995685/184047520-6a1134e9-d35a-4c7d-aca8-ae3f2fa9e8b1.png)
    
    - `C까지의 거리(5) + C-E 가중치(3)` < `E까지의 거리(11)` = 업데이트 `E(11 → 8)`
    
    ![8](https://user-images.githubusercontent.com/37995685/184047521-2a8345c6-107f-4dc1-9920-94a49b444026.png)
    
    - `D까지의 거리(13) + D-E 가중치(13)` > `E까지의 거리(8)` = 유지 `E(8 → 8)`
    - `D까지의 거리(13) + D-F 가중치(2)` < `F까지의 거리(INF)` = 업데이트 `F(INF → 15)`
    
    ![9](https://user-images.githubusercontent.com/37995685/184047522-82ef0801-2865-429f-af22-81e814b43cfe.png)
    
    - `E까지의 거리(8) + E-F 가중치(6)` vs `F까지의 거리(15)` = 업데이트 `F(15 → 14)`
4. 더 이상 움직일 수 있는 정점이 없을 경우 알고리즘을 종료합니다. 
    
    ![10](https://user-images.githubusercontent.com/37995685/184047523-5a13db16-a499-4c36-8f64-c6c3e920bb19.png)
    
    - A → B 까지의 최소비용 : 4
    - A → C 까지의 최소비용 : 5
    - A → D 까지의 최소비용 : 13
    - A → E 까지의 최소비용 : 8
    - A → F 까지의 최소비용 : 15

## 시간 복잡도 및 빅오 표기

| Best | Average |
| --- | --- |
| O(⁍) | O(⁍) |
- V : 정점의 개수
- E : 간선의 개수

## 다익스트라 알고리즘 구현 with JAVA

```java
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
  int end;
  int weight;

  Edge(int end, int weight){
    this.end = end;
    this.weight= weight;
  }

  @Override
  public int compareTo(Edge e) {
    return weight- e.weight;
  }
}

public class Main {
  static List<Edge>[] list;
	static boolean[] checked;
	static int[] distance;
  public static void main(String[] args) throws IOException{
      int V = 6; // 정점 개수
      int E = 9; // 간선 개수

      checked = new boolean[V+1];
      distance = new int[V+1];
      list = new ArrayList[V+1];

      for(int i=1; i<=V; i++){
          list[i] = new ArrayList<>();
      }

      list[1].add(new Edge(2, 4));
      list[1].add(new Edge(3, 5));
      list[2].add(new Edge(3, 11));
      list[2].add(new Edge(4, 9));
      list[2].add(new Edge(5, 7));
      list[3].add(new Edge(5, 3));
      list[4].add(new Edge(5, 13));
      list[4].add(new Edge(6, 2));
      list[5].add(new Edge(6, 6));

      dijkstra(1);
        
      for(int i=1; i<=V; i++){
        System.out.println(distance[i]);
      }
    }
    public static void dijkstra(int start) {
      PriorityQueue<Edge> queue = new PriorityQueue<>();
      Arrays.fill(distance, Integer.MAX_VALUE);
		
		  distance[start] = 0;
      queue.add(new Edge(start, 0));

      while(!queue.isEmpty()){
        Edge edge = queue.poll();

        int current = edge.end;
        int weight = edge.weight;

        if(checked[current]) continue;
        checked[current] = true;

        Iterator iter = list[current].iterator();
        while(iter.hasNext()){
          Edge next = (Edge) iter.next();

          int nextI = next.end; 
          int nextW = weight + next.weight;

          if(nextW < distance[nextI]) {
            distance[nextI] = nextW;
            queue.add(new Edge(nextI, nextW));
          }
        }
      }
    }
}
```

## 출처

- [https://ko.wikipedia.org/wiki/이진_검색_알고리즘](https://ko.wikipedia.org/wiki/%EC%9D%B4%EC%A7%84_%EA%B2%80%EC%83%89_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
- [https://yoongrammer.tistory.com/75](https://yoongrammer.tistory.com/75)
