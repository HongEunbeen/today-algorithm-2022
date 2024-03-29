## 문제
```
백준중학교에서는 학기가 끝날 무렵에 출결사항을 보고 개근상을 줄 것인지 말 것인지 결정한다. 이 학교는 이상해서 학생들이 학교를 너무 자주 빠지기 때문에, 개근상을 주는 조건이 조금 독특하다.
출결사항이 기록되는 출결은 출석, 지각, 결석이다.
개근상을 받을 수 없는 사람은 지각을 두 번 이상 했거나, 결석을 세 번 연속으로 한 사람이다.
한 학기가 4일이고, O를 출석, L을 지각, A를 결석이라고 했을 때, 개근상을 받을 수 있는 출결정보는
OOOO OOOA OOOL OOAO OOAA OOAL OOLO OOLA OAOO OAOA 
OAOL OAAO OAAL OALO OALA OLOO OLOA OLAO OLAA AOOO 
AOOA AOOL AOAO AOAA AOAL AOLO AOLA AAOO AAOA AAOL
AALO AALA ALOO ALOA ALAO ALAA LOOO LOOA LOAO LOAA 
LAOO LAOA LAAO
총 43가지이다.
한 학기는 N일이다. N이 주어졌을 때, 개근상을 받을 수 있는 출결정보의 개수를 세는 프로그램을 작성하시오.
```

## 풀이 방법
- dp 점화식 방식 
  - 재귀함수 쓰는게 코드가 깔끔할것 같은데 재귀함수 어려워서 for문 돌림
- dp에 저장할 값
  - 전체 일 수, 지각 횟수, 결석 횟수(연속)
  - dp는 3차배열로 구성
- `dp[i][j][z]`
  - i = 전체 일수
  - j = 지각 횟수
  - z = 연속 결석 횟수
- 해당 일수(i)에 개근상이 가능한 개수을 계산해 저장
  - 연속 결석 횟수(z)가 0 일 경우(연속된 결석이 없을 경우) : 전 날의 지각을 안 했을 경우 + 전 날의 지각을 한 번 했을 경우
  - 연속 결석 횟수(z)가 0이 아닐 경우(연속된 결석이 있을 경우) : 전 날의 개근상 가능한 개수를 저장 (해당 일에는 개근상 갯수 불가능)
- 결과
  - 마지막 날의 지각을 0번, 1번 했을 경우 더함
  - 마지막 날의 연속결석을 0번, 1번, 2번 했을 경우 더함
