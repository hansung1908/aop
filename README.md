# aop
- Aspect Oriented Programming (관점 지향 프로그래밍)은 임의의 함수를 프록시(임의의 메모리 공간)로 가져와 유효성 검사나 로그 같은 함수 실행 전 후로 필요로 하는 기능들을 추구하는데 쓰이는 기능이다. 이를 통해 부가적이나 공통적으로 적용해야 하는 기술들을 핵심적으로 개발하는 함수와 분리하여 보다 편리하게 다룰 수 있게 한다.

### logback
- log4j를 기반으로 제작된 로깅 라이브러리
- log4j보다 10배 빠른 퍼포먼스, 메모리 효율성 증대
- 출시 순서 : log4j -> logback -> log4j2
- 로그에 특정 레벨을 설정할 수 있음 (trace -> debug -> info -> warn -> error)
- 실운영 환경에선 퍼포먼스를 고려하여 debug를 쓰지 않고 info, warn을 사용
- 출력 방식(command, 파일, 데이터베이스 등)에 대해 설정 가능
- 설정 파일을 일정 시간마다 스캔하여 어플리케이션 중단없이 설정 변경 가능
- 별도의 프로그램 없이 자체적으로 로그 압축을 지원
- 로그 보관 기간 설정 가능

### logback 설정
- java legacy, spring의 경우, logback.xml 참조
- spring boot의 경우, logback-spring.xml 참
