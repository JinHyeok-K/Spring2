package Springtest2.Entity;

import javax.persistence.*;

@Entity // 해당 클래스를 엔티티[DB내 테이블과 맵핑(연결)] 로 사용
@Table(name = "test") // DB 사용될 테이블 이름 정하기
public class TestEntity { // 클래스

    @Id //  import javax.persistence.Id; | 기본 키 설정 pk
    @GeneratedValue ( strategy = GenerationType.IDENTITY) // import javax.persistence.GeneratedValue; 
              // auto eky (자동 번호)
    public int no;              // 필드


    @Column(name = "content") // import javax.persistence.Column; |
                // DB에서 사용할 필드 (name="content")생략 시 public String content
    public String content;      // 필드
    //public String
}


// JPA : 클래스를 DB테이블 처럼 사용
    // 1. JAVA 클래스를 엔티티화 하면 DB 테이블이 자동 생성 (클래스만 작성해도 테이블이 생성됨)

    // JAVA < ------ JPA(맵핑) JpaRepository : 조작  ------ > DB
    // entity class                                         table
    //   필드                                                 필드
    //  예) 게시물번호                                        게시물 번호
    //      게시물내용                                       게시물 내용



