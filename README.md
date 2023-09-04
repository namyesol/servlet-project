# 팀 프로젝트

팀 프로젝트의 서블릿 구현체

## 프로젝트 소개

[네이버 Works](https://naver.worksmobile.com/)나 [두레이](https://dooray.com/) 와 같은 그룹웨어. [노션](https://www.notion.so/) ,[슬랙](https://slack.com/intl/ko-kr) 참조?



## 개발 환경

- Java SDK 1.8
- Tomcat 8.5
- Servlet 3.1
- JSP 2.3
- JSTL 1.2
- Oracle Database 11g xe
- MyBatis 3.4.6



## 프로젝트 설정 방법

### Git Clone Repository

워킹스페이스 폴더에 저장소를 클론한다.

### File > Open Projects from File System

깃허브와 연동해야하므로 프로젝트를 `Workspace` 에 복사하지 말고 열어야 한다.

### Properties > Web Project Settings > Context Root > `/`

Properties > Web Project Settings > Context Root > `servlet-project` 에서 `/` 로 변경한다.

### Targeted Runtimes > Tomcat@8.5

Properties > Targeted Runtimes 에서 Tomcat 8.5 버전을 선택한다.

### Java Build Path > Java System Library

자바 버전을 1.8로 변경해준다.

### Java Build Path > Server Runtime

`Unbound` 이거나 톰캣의 버전이 `Targeted Runtimes` 에서 설정한 버전과 다를 경우 Tomcat 8.5 버전으로 변경해준다.

### Java Build Path > Web App Libraries

`src/main/webapp/WEB-INF/lib` 에 있는 jar파일들을 추가해주기 위해 `Web App Libraries` 설정이 현재 프로젝트로 되어있는지 확인한다. 설정이 안되어 있다면 `Edit...` 버튼을 눌러 현재 프로젝트를 추가한다.



## 데이터베이스 설정 방법

다음 파일들을 이용해서 설정해야만 하며 변동사항은 다른 곳에 기록될 수 없다.

- `database/00_user.sql` : 사용자 생성 및 권한 부여
- `database/01_schema.sql` : 데이터베이스 스키마 생성 (테이블, 스퀀스, 인덱스 등) DDL
- `database/02_data.sql` : 샘플 데이터 추가 DML



