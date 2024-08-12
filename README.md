퍼즐게임에는 못푸는 경우가 존재합니다.
흔히 Inversions , bottom to top에서의 빈칸의 위치 number, N (N*N 크기 패널에서의 N)의 상대적인 조합으로 인해 해결할 수 없는 경우가 존재합니다.
모든 기준은 N이 홀수 or 짝수이며 두번째 판단 요소는 바텀으로 부터의 빈칸의 위치(홀수, 짝수) 마지막으로 판단 요소하는 Inversion의 홀수, 짝수 입니다.

*해결 가능한 알고리즘

1) N이 홀수인 경우는 Inversions -> 짝수
2) N이 짝수인 경우  number -> 짝수 & Inversions -> 홀수
3) N이 짝수인 경우  number -> 홀수 & Inversions -> 짝수

이 외에는 모두 풀수 없습니다.

