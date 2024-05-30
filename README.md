# 🏷 나만의 창작활동 커뮤니티 서비스앱

![화면 캡처 2024-05-28 175026](https://github.com/ArtifinityTeam/Artifinity-PhotoBoard-SpringBoot/assets/149933307/c2fcfa25-815e-4c10-86a1-3405af93f812)

- Test ID : 123@example.com
- Test PW : 123456

<br/>

## 프로젝트 진행 및 작업 관리

### 📌 진행 사항 확인

- **Notion**에서 자세한 진행사항 보러가기:
  [![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)](https://www.notion.so/_-Artifinity-20ada978eb8d478fa805e57d1aa47342?pvs=4)

### 📄 프로젝트 자료

- **Google Docs**에서 전체적인 프로젝트 자료 보러가기:
  [![Google Docs](https://img.shields.io/badge/Google%20Docs-4285F4?style=for-the-badge&logo=googledocs&logoColor=white)](https://docs.google.com/document/d/1SQ5YhRYlxYh2oUlUc6WO0hkd465DG-5JBDu5U2qfAW8)

<br/>

## 프로젝트 소개

- Artifinity는 사진을 업로드하고 공유할 수 있는 웹 애플리케이션입니다.
- 사용자는 사진을 업로드하고, 게시물을 보고, 댓글을 남길 수 있습니다.
- 검색을 통해 다른 크리에이터들의 게시물을 구경할 수 있습니다.
- 각 크리에이터들의 다양한 게시물을 한번에 볼 수 있습니다.
<br/>

## 팀원 소개

|김기석|김서현|나현주|이유진|
|:---:|:---:|:---:|:---:|
|<img src="https://github.com/ArtifinityTeam/Artifinity-PhotoBoard-SpringBoot/assets/149933307/a0100975-a124-4757-bb15-a6a65ac7acde"  width="300" height="280">|<img src="https://github.com/ArtifinityTeam/Artifinity-PhotoBoard-SpringBoot/assets/127668637/cf00dc2e-3953-4536-b1e8-c2e8dd216df9"  width="300" height="280">|<img src="https://github.com/ArtifinityTeam/Artifinity-PhotoBoard-SpringBoot/assets/149933307/2c409a25-2858-456b-8d37-cc3635d27efe"  width="300" height="280">|<img src="https://github.com/ArtifinityTeam/Artifinity-PhotoBoard-SpringBoot/assets/149933307/623f65bc-f7e2-472b-bfa7-3e1060243372"  width="300" height="280">|
|[@chundae](https://github.com/chundae)|[@kshhyun](https://github.com/kshhyun)|[@HyeonJooooo](https://github.com/HyeonJooooo)|[@hbyjna](https://github.com/hbyjna)|
<br/>


## 1. 개발 환경 기술 스택 및 도구

### Front-end
- HTML / CSS / JSP / JavaScript

### Back-end
- Spring Boot / JPA

### Database
- MySQL

### 협업 툴
- **[문서작성]** Notion / Google Docs
- **[개발도구]** Visual Studio code (VSCode) / IntelliJ (Gradle Project)
- **[디자인]** Figma

<br/>


## 2. 주요 기능

- 사용자 등록 및 로그인
- 사진 업로드
- 게시물 보기 및 댓글 작성
- 각 크리에이터의 게시물 모아보기
- 크리에이터를 위한 외주 게시판 
<br/>

## 3. 프로젝트 구조

    ```treebash
    ├─build
    │  ├─classes
    │  │  └─java
    │  │      └─main
    │  │          └─org
    │  │              └─teamproject
    │  │                  └─teamproject
    │  │                      ├─Controller
    │  │                      ├─Service
    │  │                      └─Vo
    │  ├─generated
    │  │  └─sources
    │  │      ├─annotationProcessor
    │  │      │  └─java
    │  │      │      └─main
    │  │      └─headers
    │  │          └─java
    │  │              └─main
    │  ├─resources
    │  │  └─main
    │  │      ├─...
    │  └─tmp
    │      ├─...
    ├─gradle
    │  └─wrapper
    ├─out
    │  └─...
    └─src
        ├─main
        │  ├─java
        │  │  └─org
        │  │      └─teamproject
        │  │          └─teamproject
        │  │              ├─Controller
        │  │              ├─Service
        │  │              └─Vo
        │  ├─resources
        │  │  ├─static
        │  │  │  ├─css
        │  │  │  ├─icons
        │  │  │  ├─images
        │  │  │  ├─js
        │  │  │  └─uploads
        │  │  └─templates
        │  └─webapp
        │      ├─upload
        │      └─WEB-INF
        │          └─views
        │              └─html
        └─test
            └─java
                └─org
                    └─teamproject
                        └─teamproject
    ```
<br/>


## 4. 개발 기간

- 전체 개발 기간 : 2024.04.05 ~ 2024.05.26
- UI 구현 : 2024.04.05 ~ 2024.04.19
- 기능 구현 : 2024.04.20 ~ 2024.05.26
<br/>
