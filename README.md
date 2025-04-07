# Project_Jump_And_Shoot
TUKorea Smartphone Game Programming Term Project

# 게임 컨셉
Jump And Shoot은 양 옆에서 다가오는 몬스터들을 총으로 쏘며 점프하는 게임이다.

플레이어는 화면의 왼쪽 또는 오른쪽을 터치하여, 터치한 방향으로 총을 발사하여 몬스터를 처치할 수 있다.  
이 때 플레이어는 총을 발사한 방향의 반대 방향으로 점프를 하게 되며, 점프 후 착지하기 전 까지는 총을 발사할 수 없다.  
게임 시작 시 플레이어는 맵 중앙에 위치하게 되며, 플레이어가 맵 중앙에 위치한 상태에서 오른쪽 또는 왼쪽으로 총 2번 점프하여 이동할 수 있다.  
만약 플레이어가 맵의 끝으로 가면 더 이상 총을 발사하거나 이동할 수 없게된다.  

# 개발 범위
### 총돌 처리
이 게임에서의 핵심 개발 요소는 충돌처리이다. '플레이어 - 몬스터' 충돌과, '플레이어가 발사한 총알 - 몬스터' 충돌 처리가 칠요하다.  
기획한 게임 특성 상 오브젝트가 회전하지 않으므로, AABB(Axis-Aligned Bounding Box)만으로 충분하다. AABB는 오브젝트가 가지는 4개의 꼭짓점 위치만 비교하면 되기 때문에 하드웨어 성능 여유가 부족한 임베디드 환경에서 빠르게 충돌 처리가 가능할 것이다.

### 터치 이벤트 처리
플레이어가 총을 쏘고 점프하기 위해서는, 화면에 터치 이벤트 처리가 필요하고, 또한 터치된 방향을 알아내야 한다. 터치 방향은 화면 중심을 기준으로 왼쪽이면 왼쪽, 오른쪽이면 오른쪽으로 처리되도록 할 것이다.

### 연출 효과
플레이어가 총을 발사하거나, 플레이어가 바닥에 착지할 때 좀 더 실감나는 느낌을 주기 위해 연출 기법을 구현할 계획이다. lerp(선형보간), sin(삼각함수)등 여러 수학적 기법을 사용하여 다양한 연출을 추가할 것이다.  
예를 들어 플레이어가 숨을 쉬는 애니메이션은 sin함수를 통해 스프라이트가 조금씩 위 아래로 늘어났다 줄어들었다 하는 식으로 구현할 수 있다.  
플레이어 착지 애니메이션은 플레이어 스프라이트에 가로로 살짝 눌리는 느낌을 주면 플레이어가 바닥에 착지한다는 느낌을 더 살릴 수 있다.  

# 게임의 흐름
(이미지 추가 예정)

# 개발 일정
### 1주차
기초 게임 프레임워크 구축 및 필요 리소스 확보

### 2주차
게임 모드 전환 시스템 구축  
수학 연산 모듈 구축(선형 보간, 거리 측정, 삼각 함수 등)

### 3주차
플레이 모드(Play Mode)에 필요한 객체 클래스 및 뷰 구현  
터치 이벤트 처리

### 4주차
'플레이어 - 몬스터' 충돌과 '플레이어 총알 - 몬스터' 충돌 처리 구현  
해당 충돌처리를 위한 AABB 시스템 구축(디버그 빌드 시 AABB 영역이 표시되는 것도 포함)

### 5주차
메인 모드인 플레이 모드 구현

### 6주차
타이틀 모드, 일시정지 모드 등 다른 모드들을 구현

## 7주차
UI 다듬기, 사운드 추가 및 유저 데이터 처리 시스템 구축

## 8주차
성능 최적화 및 디버깅
