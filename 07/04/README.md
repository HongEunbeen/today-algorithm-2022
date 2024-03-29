## 문제
```
세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다.
보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다.
즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
```

## 풀이 방법
- DFS로 품(미로찾기 인줄 알고 BFS로 풀었는데 깊이를 탐색해야 하는 거임!)
- 알파벳(26)의 배열을 만들고 DFS가 들린 곳이면 true로 변경 -> 대문자만 나오기 때문에 'A' 빼주면(0 ~ 26) 배열 사용 가능
  - 이때, 중복된 알파벳 발견 시 되돌아감(return)
- 중복된 알파벳 발견으로 되돌아 가면, 다른 루트로 DFS 다시 돌리기 위해 해당 알파벳은 false
