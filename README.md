# 나만의 창작활동 커뮤니티 서비스앱

![화면 캡처 2024-05-28 175026](https://github.com/ArtifinityTeam/Artifinity-PhotoBoard-SpringBoot/assets/149933307/c2fcfa25-815e-4c10-86a1-3405af93f812)

## 프로젝트 소개

- Artifinity는 사진을 업로드하고 공유할 수 있는 웹 애플리케이션입니다.
- 사용자는 사진을 업로드하고, 게시물을 보고, 댓글을 남길 수 있습니다.
- 검색을 통해 다른 크리에이터들의 게시물을 구경할 수 있습니다.
- 크리에이터들이 게시한 다양한 게시물을 한번에 볼 수 있습니다.

## 팀원 소개

- **팀장:** 이현주 ([@HyeonJooooo](https://github.com/HyeonJooooo))
- **프론트엔드 개발자:** 김영희 ([@youngheeKim](https://github.com/youngheeKim))
- **백엔드 개발자:** 박철수 ([@chulsooPark](https://github.com/chulsooPark))

## 배포 주소

[https://artifinity-photoboard-springboot.com](https://artifinity-photoboard-springboot.com)

## 개발 환경 설정

### 요구 사항

- Java 11 이상
- Gradle
- MySQL

### 설치 및 실행

1. 저장소를 클론합니다:
    ```bash
    git clone https://github.com/ArtifinityTeam/Artifinity-PhotoBoard-SpringBoot.git
    cd Artifinity-PhotoBoard-SpringBoot
    ```

2. 데이터베이스를 설정합니다:
    ```sql
    CREATE DATABASE artifinity;
    ```

3. `application.properties` 파일을 설정합니다:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/artifinity
    spring.datasource.username=root
    spring.datasource.password=password
    ```

4. 애플리케이션을 빌드하고 실행합니다:
    ```bash
    ./gradlew build
    ./gradlew bootRun
    ```

## 기술 스택

- **프론트엔드:** Thymeleaf, HTML, CSS, JavaScript
- **백엔드:** Spring Boot, JPA
- **데이터베이스:** MySQL

## 주요 기능

- 사용자 등록 및 로그인
- 사진 업로드 및 삭제
- 게시물 보기 및 댓글 작성

## 프로젝트 구조

src/
├── main/
│ ├── java/
│ │ └── com/artifinity/
│ │ ├── controller/
│ │ ├── service/
│ │ └── repository/
│ └── resources/
│ ├── static/
│ └── templates/
└── test/


## 개발 기간

- 2024년 1월 ~ 2024년 4월

## 문제 해결

- 데이터베이스 연결 문제: 설정 파일에서 URL과 자격 증명을 확인하세요.
- 빌드 실패: 의존성을 확인하고 `./gradlew clean build`를 실행하세요.

## 라이선스

이 프로젝트는 MIT 라이선스에 따라 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참고하세요.
