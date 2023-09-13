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

##### Example 2.2.2 Form Data를 서블릿에서 변수에 저장하는 방법

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

[서블릿에서 post body를 어떻게 읽는가?](https://jongqui.tistory.com/9)

- HTTP API에서 데이터를 보낼 때 사용합니다.
- 자바스크립트로 메시지를 보내기 때문에 jQuery나 Axios 등의 라이브러리를 사용해서 요청을 보냅니다.
- 데이터 형식은 JSON을 주로 사용하며 요청의 메소드 타입과 컨테츠 타입을 명시합니다.
- HTTP Method :  [POST | DELETE | PATCH ]
- `Content-Type` : `application/json`

HTTP Message Body에서 원하는 값을 읽어오는 방법은 요청의 `Content-Type`에 따라서 달라집니다.

### 2.3.1  `text/plain`

`content-Type `이 `text/plain`인 HTTP Message를 처리하는 방법입니다.

##### Example 2.3.1 jQuery로 요청보내는 방법

```javascript
$.ajax({
    type : "post",
    url : "/users",
    contentType: "application/json",
    data : {
        "email": 'hello@example.com',
    	"password": 'hello'
    },
    success : function(response) {
        console.log(response);
    },
    error : function(error) {
        console.log(error);
    }
})
```

##### Example 2.3.1 Axios로 요청보내는 방법

```javascript
axios.post('/users', {
    "email": 'hello@example.com',
    "password": 'hello'
  }, {
  	'Content-Type': 'application/json'  
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```

##### Example 2.3.1 `content-type=text/plain` 인 메시지 바디를 서블릿에서 문자열에 저장하는 방법

```java
BufferedReader reader = request.getReader();
String messageBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
System.out.println("====MessageBody====");
System.out.println(messageBody);
System.out.println("===================");
```

- `HttpServletRequest` 타입인 `request` 객체의  `getReader` 메서드를 사용해서 HTTP Request의 Message Body를 읽을 수 있는 `BufferedReader` 타입의 객체 `reader` 를 가지고 올 수 있습니다.
- `BufferedReader` 타입의 객체 `reader`의 `lines` 메서드는 Http Message Body의 각 줄을 `Stream<String>` 유헝으로 반환합니다.
- Stream API의 `collect` 메서드를 사용해서 `Stream<String>`  유형인 HTTP Message Body 를 `String`으로 형 변환합니다.



#### 2.3.2 `application/json`

`content-Type `이 `application/json`인 HTTP Message를 처리하는 방법입니다.

##### Example 2.3.2 jQuery로 요청 보내는 방법

```javascript
$.ajax({
    type : "post",
    url : "/users",
    contentType: "application/json",
    data : {
        "email": 'hello@example.com',
    	"password": 'hello'
    },
    success : function(response) {
        console.log(response);
    },
    error : function(error) {
        console.log(error);
    }
})
```

##### Example 2.3.2 Axios로 요청 보내는 방법

```javascript
axios.post('/users', {
    "email": 'hello@example.com',
    "password": 'hello'
  }, {
  	'Content-Type': 'application/json'  
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```

##### Example 2.3.3 `content-type=application/json` 인 메시지 바디를 서블릿에서 문자열에 저장하는 방법

```java
BufferedReader reader = request.getReader();
String messageBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
System.out.println("====MessageBody====");
System.out.println(messageBody);
System.out.println("===================");
```

```
====MessageBody====
{
    "email": "hello@example.com",
    "password": "hello"
}
===================
```

- `content-type`이 `application/json` 인 HTTP Message Body를 `messageBody` 문자열에 저장한 상태입니다.
- `HttpServletRequest` 타입인 `request` 객체의  `getReader` 메서드를 사용해서 HTTP Request의 Message Body를 읽을 수 있는 `BufferedReader` 타입의 객체 `reader` 를 가지고 올 수 있습니다.
- `BufferedReader` 타입의 객체 `reader`의 `lines` 메서드는 Http Message Body의 각 줄을 `Stream<String>` 유헝으로 반환합니다.
- Stream API의 `collect` 메서드를 사용해서 `Stream<String>`  유형인 HTTP Message Body 를 `String`으로 형 변환합니다.

#### 2.3.3 `content-type=application/json` 인 메시지 바디를 서블릿에서 객체에 저장하는 방법

`content-type` 이 `application/json` 인 요청을 문자열에 저장했습니다. 이제 이 문자열을 객체로 저장하는 방법을 알아봅시다.

JSON 형식인 문자열을 객체로 저장하기 위해서는 **JSON Object Mapper**가 필요합니다. 오브젝트 매퍼 중의 하나로 스프링에서도 사용 중인 [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)을 `/WEB-INF/LIB/ ` 폴더에 추가해줍니다. `jackson-databind` 라이브러리는 `jackson-core`와  `jackson-annotations` 라이브러리를 의존하고 있기 때문에 3개의 Jar 파일을 추가해주어야 합니다.

문자열을 객체로 매핑하기 위한 클래스를 생성하여 줍니다.

UserData.java

```java
public class UserData {

	private String email;
	private String password;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserData[email=" + this.email + ", password=" + this.password + "]";
	}
}
```

##### Example 2.3.3 ObjectMapper 객체 사용하기

```java
import com.fasterxml.jackson.databind.ObjectMapper;
ObjectMapper objectMapper = new ObjectMapper();
```

- `Jackson` 라이브러리에서 문자열을 객체에 매핑할 때 사용하는 `ObjectMapper`  객체를 가지고 옵니다.

##### Example 2.3.3 ObjectMapper로 문자열을 객체에 매핑하기

```java
BufferedReader reader = request.getReader();
String messageBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
System.out.println("====MessageBody====");
System.out.println(messageBody);
System.out.println("===================");

UserData userData = objectMapper.readValue(messageBody, UserData.class);
System.out.println(userData);
```

```
====MessageBody====
{
    "email": "hello@example.com",
    "password": "hello"
}
===================
UserData[email=hello@example.com, password=hello]
```

- `objectMapper.readValue` 메서드를 사용합니다.
- 첫 번째 매개변수로 `messageBody` 문자열을 두 번째 매개변수로 매핑하려는 클래스 `UserData.class` 를 지정합니다.
- 반환되는 값은 `messageBody`를 `UserData.class` 의 프로퍼티에 값을 매핑한 `UserData` 객체입니다. 반환되는 값을 변수 `userData`에 저장합니다.
- `JsonProcessingException` :  `objectMapper` 는 매핑 과정 중 에러가 발생하면 `JsonProcessingException ` 을 발생시킵니다. **TryCatch** 블록이나 **Throws** 키워드를 사용하여 에러 처리를 해줍니다.
- 이 예제에서는 예외를 신경쓰지 않기 위해 **Throws** 키워드를 사용합니다.

##### Example 2.3.3 ObjectMapper로 객체를 JSON형식의 문자열로 변환하기

```java
String payload = objectMapper.writeValueAsString(userData);

response.setContentType("application/json");
response.getWriter().write(payload);
```

- objectMapper의 `writeValueAsString` 메서드를 사용해서 객체를 JSON 형식의 문자열로 변환할 수 있습니다.
- `HttpServletResponse` 의 `setContentType` 메서드로 HTTP Response의 `content-type`을 `application/json` 으로 설정할 수 있습니다.
- `HttpServletResponse` 의 `getWriter` 메서드를 호출해서 HTTP 응답 본문을 쓸 수 있는 `PrintWriter`  개체를 불러올 수 있습니다.
- `PrintWriter.write` 메서드를 사용해서 `userData` 객체를 문자열로 변환한  `payload` 문자열을 응답 본문에 넣어서 보내줄 수 있습니다. 

##### Example 2.3.3 전체 코드

com.controller.example.JsonExampleServlet.java

```java
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, JsonProcessingException, IOException 
{
    BufferedReader reader = request.getReader();
    String messageBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
    System.out.println("====MessageBody====");
    System.out.println(messageBody);
    System.out.println("===================");

    ObjectMapper objectMapper = new ObjectMapper();;
    UserData userData = objectMapper.readValue(messageBody, UserData.class);
    System.out.println(userData);

    String payload = objectMapper.writeValueAsString(userData);

    response.setContentType("application/json");
    response.getWriter().write(payload);
}
```



### 3. HttpServletResponse

서블릿에서 클라이언트에게 응답하는 3가지 방법

#### Response.getWriter().wirte()

#### Response.SendRedirect

#### `RequestDispatcher.forward` 

다른 서블릿이나 JSP로 이동할 수 있는 기능. 서버 내부에서 다시 호출이 발생한다.

```java
String nextPage = ".jsp";
RequestDispatcher dispatc
```



#### 


## Oracle Database

https://oracle.readthedocs.io/en/latest/index.html

> FROM -> WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY



### 1.SQL 문법 순서

SQL 쿼리를 작성 할 때 키워드의 순서는 다음과 같다.

1. SELECT
2. FROM
3. WHERE
4. GROUP BY
5. HAVING
6. ORDER BY



### 2. SQL 키워드 실행 순서

SQL 키워드의 실행 순서는 아래와 같다. **문법의 순서와 실행 순서가 다르다는 것**을 알 수 있다.

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



### 3. TOP-N 쿼리란?

상위 n개의 데이터를 추출하는 쿼리 e.g. *멜론 차트 Top 100*, 왕가탕후루 판매 순위 10



#### ROWNUM Pseudo Column

`ROWNUM` 가상 열은 SELECT 해온 데이터에 행 번호를 붙힌다.

##### Example 3.1.1 ROWNUM 예제

```sql
SELECT ROWNUM, E.* FROM emp E;
```

`WHERE` 절과 함께 행의 개수를 제한하는 용도로 사용할 수 있다.

##### Example 3.1.2 ROWNUM 행 개수 제한하기

```sql
SELECT ROWNUM, E.*
FROM emp E
where ROWNUM <= 10;
```

- 전체 결과행 중 1번부터 10번까지 가져온다.



#### Top-N Query

`ROWNUM` , `WHERE` , `ORDER BY` 절을 조합해서 상위 N개의 쿼리를 구할 수 있다.

##### Example 3.1.2 TOP-N Query 잘못된 사용법

```sql
SELECT ROWNUM, E.*
FROM emp E
WHERE ROWNUM <= 10
ORDER BY sal DESC;
```

- 연봉 순으로 내림차순 정렬 한 다음 10개행의 목록을 구하는 쿼리이다. 
- 실행 결과는 상위 연봉 10명의 목록이 나올 것 같지만 그렇지 않다. 
- SQL 구문의 실행 순서상 `WHERE` 절이 `ORDER BY` 절보다 앞서 실행되므로 전체 테이블에서 10개행의 목록을 가지고 온 후 정렬하기 때문이다.

##### Example 3.1.3 서브쿼리를 이용한 TOP-N QUERY 올바른 사용법

```sql
SELECT *
FROM (
    SELECT ROWNUM, E.*
	FROM emp E
	ORDER BY sal DESC)
WHERE ROWNUM <= 10;
```

- `ORDER BY` 절이 `WHERE` 절보다 먼저 실행되어야 상위 N개의 행을 구할 수 있다.
- `ORDER BY` 을 적용해서 연봉 순으로 내림차순 정렬하는 쿼리를 서브쿼리로 사용한다.
- 서브쿼리에서 반환한 값을 `WHERE`  절을 사용해 필터링한다.



### INDEX 란?



### 



#### Hints?



### MyBatis



`<![CDATA[]]>` 란





### JSP

