package Springtest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class);
    }

}

/* 패키지 구조 [MVC 구조]
    1. src 
        2. main
            3. java : 백엔드
                4. 상위(root) 패키지 [ 임의(이름) :일반적으로 홈페이지 명]
                    5. controller 패키지
                    5. dto 패키지
                    5. dao(entity) 패키지
                    5. service 패키지
                    5. Start class[ 임의(이름) ]
                    
            3. resources : 프론트엔드, 설정 파일
                4. static : js, css, img 등
                4. templates : html 등


*/

// * API
    // 1. 스프링 :java를 이용한 미리 만들어진 다양한  API들
        // 스프링부트 : 스프링 내 자주 사용하는 API들의 기본 셋팅
            //  @SpringBootApplication
            //  1. MVC 컴포넌트 기본값 셋팅
            //  2. tomcat 내장 서버 지원 세팅
            //  3. Restful api  : HTTP(URL) 를 이용한 자원(data)공유
        // 1. SpringApplication.run(현재클래스명.class); 스프링 실행
    // 2. 타임리프 : 템플릿 엔진( 정적 문서에 데이터를 넣어주는 프로그램 )
        //  타임리프 --> JS[ O ]
        // 템플릿 엔진 :
            // 백엔드 : 1. JSP(스프링 권장 X : 빌드시 확장자가 war)    2. 타임리프    3. 머스테치 (스프링 공식)
            // 프론트엔드 : 1.HTML, JS, React, Vue.js
        // 백엔드(JAVA/Spring ) :  1. DB 처리 2. Restful API 제작
        // 프론트엔드( JS )      :  1. Restful API URL 을 이용한 데이터 교환

        // 1. view <---- 템플릿 엔진 ----> controller
            //   * Spring
            //  1. 클라이언트가 URL 요청했을 경우 @Controller 내 에서 URL 찾기


    // 3. JDBC(데이터 베이스 연결)
        // 1. DAO 방식 : 순수 자바 형식의 SQL 작성
        // 2. SQL Mapper [ xml 방식 ] : MyDatis(DBMS)
        // 3. JPA : JDBC (JAVA <---연결---> DB) API
            // * 반복되는 SQL 를 JAVA 코드로 변경
                // --> SQL 다량 암기
                // JAVA 개발자 + SQL 공부 : X(힘듬)
                // SQL 문법 --> JAVA 문법
            // JPA 사용 목적은 SQL 작성코드를 줄이기 위함

        // 1. Spring Data  JPA
        // 2. MySQL

















