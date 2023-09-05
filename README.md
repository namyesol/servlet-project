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



## Servlet

### 1. Http Protocol

클라이언트와 서버가 통신할 때 사용하는 프로토콜입니다.

#### 1.1 HTTP Message 구조

https://developer.mozilla.org/ko/docs/Web/HTTP/Messages

클라이언트와 서버가 통신할 때 HTTP 프로토콜을 사용해서 아래와 같이 메시지를 보냅니다.

##### Example 1.1.1 HTTP Message

```http
POST /login HTTP/1.1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,ko;q=0.8
Cache-Control: max-age=0
Connection: keep-alive
Content-Length: 44
Content-Type: application/x-www-form-urlencoded
Cookie: JSESSIONID=3B089B0BECFFABABFEAAB90B585D5441

email=hello%40example.com&password=1234
```

- HTTP Message는 헤더와 바디로 나누어 집니다. 
- 바디에 내용이 있을 경우 헤더와 구분하기 위해 줄 바꿈을 사용합니다.

##### Example 1.1.2 HTTP Message Header

```http
POST /login HTTP/1.1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,ko;q=0.8
Cache-Control: max-age=0
Connection: keep-alive
Content-Length: 44
Content-Type: application/x-www-form-urlencoded
Cookie: JSESSIONID=3B089B0BECFFABABFEAAB90B585D5441
```

##### Example 1.1.3 HTTP Message Body

```http
email=hello%40example.com&password=1234
```

- 본문의 존재 유무 및 크기, 타입은 HTTP 헤더에 명시됩니다.
- 본문은 요청의 마지막 부분에 들어갑니다. 모든 요청에 본문이 들어가지는 않습니다. `GET`, `HEAD`, `DELETE` , `OPTIONS`처럼 리소스를 가져오는 요청은 보통 본문이 필요가 없습니다. 일부 요청은 업데이트를 하기 위해 서버에 데이터를 전송합니다. 보통 (HTML 폼 데이터를 포함하는) `POST` 요청일 경우에 그렇습니다.
- `Content-Type` : 본문의 형식 e.g. `application/x-www-form-urlencoded`, `application/json`,  `text/html` 
- `Content-Length` : 본문의 크기를 나타냅니다.



##### HTTP Message 구조 요약

1. 시작 줄(start-line)에는 실행되어야 할 요청, 또은 요청 수행에 대한 성공 또는 실패가 기록되어 있습니다. 이 줄은 항상 한 줄로 끝납니다.
2. 옵션으로 HTTP 헤더 세트가 들어갑니다. 여기에는 요청에 대한 설명, 혹은 메시지 본문에 대한 설명이 들어갑니다.
3. 요청에 대한 모든 메타 정보가 전송되었음을 알리는 빈 줄(blank line)이 삽입됩니다.
4. 요청과 관련된 내용(HTML 폼 콘텐츠 등)이 옵션으로 들어가거나, 응답과 관련된 문서(document)가 들어갑니다. 본문의 존재 유무 및 크기는 첫 줄과 HTTP 헤더에 명시됩니다.



### 2. HttpServletRequest

HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법에는 3가지가 있습니다.

- [Get] Query Parameter
  - /posts?**page=14&size=10**
  - 메시지 바디 없이 URL의 쿼리 파라미터에 데이터를 포함해서 전달하는 방식입니다.
  - 검색, 필터, 페이지네이션 등에 많이 사용됩니다.
- [POST] HTML Form
  - 메시지 바디에 쿼리 파라미터 형식으로 전달합니다.
  - `Content-Type` : `application/x-www-form-urlencoded` 로 Form Data을 HTTP Header에 명시합니다.
  - 회원 가입, 로그인, 상품주문, 후기 작성, 댓글 달기 등에 HTML Form Data를 사용합니다.
- [HTTP METHOD] Message Body
  - HTTP API에서 데이터를 보낼 때 사용합니다.
  - 자바스크립트로 메시지를 보내기 때문에 jQuery나 Axios 등의 라이브러리를 사용해서 요청을 보냅니다.
  - 데이터 형식은 JSON을 주로 사용하며 요청의 메소드 타입과 컨테츠 타입을 명시합니다.
  - HTTP Method :  [POST | DELETE | PATCH ]
  - `Content-Type` : `application/json`



#### 2.1 [GET] Query Parameter

아래와 같이 `HttpServletRequest.getParameter` 메서드를 사용해서 쿼리 파라미터를 가져올 수 있습니다.

##### Example 2.1.1 Query Parameter 의 형식

```http
/posts?page=3&size=4
```

- `/{url}` 다음에 `?` 를 붙여서 쿼리 파라미터임을 명시합니다.
- `?이름=값` 형식으로 쿼리 파라미터를 선언합니다.
- `?이름1=값1&이름2=값2` 형식으로 다수의 쿼리 파라미터를 사용할 수 있습니다.
- `?이름1=&이름2=값2` 형식으로 값이 없는 파라미터를 선언할 수 있습니다다.  `이름1` 의 값은 `""`  입니다. 

##### Example 2.1.2 Query Parameter 를 서블릿에서 변수에 저장하는 방법

```java
String pageParam = request.getParameter("page");
// pageParam가 null이 아니고 ""이 아니면 문자열에서 숫자형으로 변환합니다.
if (pageParam != null && !pageParam.isEmpty()) {
    page = Integer.parseInt(pageParam);
}

String sizeParam = request.getParameter("size");
if (sizeParam != null && !sizeParam.isEmpty()) {
    size = Integer.parseInt(sizeParam);
}
```



#### 2.2 [POST] HTML Form Data

##### Example 2.2.1 HTML Form

```html
<form action="/login" method="post">
    <input type="text" name="email"/>
    <input type="password" name="password"/>
    <button type="submit">Login</button>
</form>
```

- `Content-Type` : `application/x-www-form-urlencoded`
- 메시지 바디에 쿼리 파라미터 형식으로 데이터를 전송합니다.

```http
POST /login HTTP/1.1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,ko;q=0.8
Cache-Control: max-age=0
Connection: keep-alive
Content-Length: 44
Content-Type: application/x-www-form-urlencoded
Cookie: JSESSIONID=3B089B0BECFFABABFEAAB90B585D5441

email=hello%40example.com&password=1234
```

##### Example 2.2.2 Form Data를 서블릿에서 변수에 할당하는 방법

```java
String email = request.getParameter("email");
if (pageParam == null && pageParam.isEmpty()) {
   response.send
}

String password = request.getParameter("password");
if (sizeParam != null && !sizeParam.isEmpty()) {
    size = Integer.parseInt(sizeParam);
}
```

- Query Parameter를 변수에 저장하는 방법과 똑같이  `HttpServletRequest.getParameter` 메서드를 사용합니다.



> [GET] Query Parameter 형식으로 클라이언트에서 서버로 데이터를 전달할 때는 데이터가 URL 주소에 포함됩니다. 따라서 HTTP Message Body를 사용하지 않기 때문에 `content-type`이 없습니다.
>
> [POST] HTML Form 형식으로 클라이언트에서 서버로 데이터를 전달할 때는 HTTP 메시지 바디에 해당 데이터를 포함해서 보내기 때문에 메시지 바디에 포함된 데이터가 어떤 형식인지 `content-type` 을 꼭 지정해야합니다. 이렇게 HTML Form 으로 데이터를 전송하는 형식을 `application/x-www-form-urlencoded`라 합니다.



#### 2.3 HTTP Message Body

#### 2.3.1 `text/plain`



#### 2.3.2 `application/json`



### 3. HttpServletResponse



## Oracle

https://oracle.readthedocs.io/en/latest/index.html

> FROM -> WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY



**SQL 문법 순서**

1. SELECT
2. FROM
3. WHERE
4. GROUP BY
5. HAVING
6. ORDER BY



**오라클의 쿼리 실행 순서** 

1. FROM : 테이블 선택
2. ON : 조인 조건 확인
3. JOIN : 테이블 조인
4. WHERE : 데이터 추출 조건 확인
5. GROUP BY : 특정 칼럼으로 데이터 그룹화
6. HAVING : 그룹화 이후 데이터 추출 조건 확인
7. SELECT : 데이터 추출
8. COUNT, DISTINCT, AVG 등 집계 함수 사용.
9. ORDER BY : 정렬



> Alias(별칭)은 FROM, SELECT, ORDER BY 절에서만 사용 가능합니다.



### INDEX 란?



#### TOP-N 쿼리란?



#### Paginations ? 



#### Hints?



### MyBatis



`<![CDATA[]]>` 란





### JSP

