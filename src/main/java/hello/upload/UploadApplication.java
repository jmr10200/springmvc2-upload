package hello.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadApplication.class, args);
	}

}

/** 파일 업로드 */
// 일반적으로 사용하는 HTML Form 을 통한 전송 방식에는 다음 두 가지 방식이 있다.
// 1. application/x-www-form-urlencoded
// 2. multipart/form-data

/* 1. application/x-www-form-urlencoded 방식 */
// HTTP 폼 데이터를 서버로 전송하는 가장 기본적인 방법
// Form 태그에 별도의 enctype 옵션이 없으면 웹 브라우저는 요청 HTTP 메시지의 헤더에 다음 내용을 추가한다.
//   Content-Type: application/x-www-form-urlencoded
// 그리고 폼에 입력한 전송할 항목을 HTTP Body 에 문자로 username=kim&age=20 과 같이 & 로 구분해 전송한다.

// 파일을 업로드 하려면 문자가 아니라 바이너리 데이터를 전송해야 한다.
// 문자를 전송하는 이 방식으로는 파일을 전송하기 어렵다.
// 또한, 폼을 전송할 때 파일만 전송하는 것이 아니라 문자도 함께 전송되는 경우가 많다.
// 예를들면, 이름, 나이는 문자로, 첨부파일은 바이너리로 함께 전송하는 것이다.
// 이 문제를 해결하기 위해 HTTP 는 multipart/form-data 라는 전송 방식을 제공한다.

/* 2. multipart/form-data 방식 */
// 사용하기위해서는 Form 태그에 enctype="multipart/form-data" 를 지정해야 한다.
// 다른 종류의 여러 파일과 폼 내용을 함께 전송할 수 있어서 multipart 라 한다.

// 폼의 입력 결과로 생성된 HTTP 메시지 예
// ------XXX
// Content-Disposition: form-data; name="username"
// kim
// ------XXX
// Content-Disposition: form-data; name="age"
// 20
// ------XXX
// Content-Disposition: form-data; name="file1"; filename="intro.png"
// Content-Type: image/png
// 01235sa32gkl234jh63j436lkjfiwe234kjdsfu....
// ------XXX--   (끝에는 -- 추가)

// 폼의 입력 결과로 생성된 HTTP 메시지는 각각의 전송 항목이 구분되어있다.
// Content-Disposition 이라는 항목별 헤더가 추가되고 부가 정보가 담긴다.
// 파일의 경우 filename 과 Content-Type 이 추가되고 바이너리 데이터가 전송된다.
// 즉, 이렇게 각각의 항목을 구분해서 한번에 전송하는 방식이다.

// Part
// multipart/form-data 는 application/x-www-form-urlencoded 와 비교해서
// 매우 복잡하고 부분(Part) 로 나누어져 있다.