package Springtest2.controller;

import Springtest2.Entity.TestEntity;
import Springtest2.Entity.TestRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home") //  현재 Class 는 home 으로부터 맵핑 된다.
public class indexcontroller {

    @GetMapping("/main") // 페이지 요청
    public String home() {
        return "main";
    } // 타임리프 사용시 리턴값은 html명 | Response  요구가 없을 경우 타임리프가  HTML 을 반환


    @Autowired
    TestRepository testRepository;


    // 2. main.js 내 작성 메소드가 요청하는 url
    @GetMapping("/save") // 데이터 요청
    @ResponseBody   // Response(응답)Body(객체) : Java 객체를 반환하겠다. 
    public String getdata(HttpServletRequest request) {
        // 0 변수 요청하기
        String content = request.getParameter("content");

        // 1. 엔티티 생성
        TestEntity testEntity = new TestEntity();
        testEntity.content = content;
        // 2 엔티티 save를 해주는
        testRepository.save(testEntity);
        // 3. 반환
        return "작성 성공";
        //    return "AAA";
    }

    // 3. main.js 내 호출 메소드가 요청하는 url 정의
    @GetMapping("/getlist")
    // @ResponseBody 뺀 이유 // HttpServletRequest  사용
    public void getlist(HttpServletResponse response) {

        // 1. 모든 엔티티를 호출하기
        List<TestEntity> testEntity = testRepository.findAll();
        // 2. JSON 화 하기
        JSONArray jsonArray = new JSONArray(); // 1. json 리스트 선언
        for (TestEntity entity : testEntity) { // 2. 모든 엔티티 반복문
            // 3. json 객체 선언
            JSONObject jsonObject = new JSONObject();
            // 4. json 객체 엔트리(키:값)설정 | 데이터 설정
            jsonObject.put("no", entity.no);
            jsonObject.put("content", entity.content);
            // 5. json 객체를 json 리스트에 추가
            jsonArray.put(jsonObject);

        }

        try {
            response.setCharacterEncoding("UTF-8"); // 응답객체 한글
            response.setContentType("application/json"); // 응답 객체 json 형식
            response.getWriter().print(jsonArray); //응답[json리스트]
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/delete")
    @ResponseBody
    @Transactional  // transaction 넣는것을 권장 (호출 제외)
    public String delete(HttpServletRequest request){
        // 0. 변수 요청
        int no = Integer.parseInt(request.getParameter("no"));
        // 1. pk 값 이용한 엔티티 찾기
        Optional<TestEntity> optionalTestEntity = testRepository.findById(no);
        // 2. Optional 객체 내 엔티티  빼옴
        TestEntity entity = optionalTestEntity.get();
        // 3. 삭제
        testRepository.delete(entity);

        // 바로 삭제 (2+3)
        // testRepository.delete(optionalTestEntity.get());


        return "1";
    }



    @GetMapping("/update")
    @ResponseBody
    @Transactional // 트랜잭션 어노테이션 : 일 단위 [결과 : commit 혹은 rollback]
    public String update(HttpServletRequest request){
        // 0. 변수 요청
        int no =Integer.parseInt(request.getParameter("no"));
        String content = request.getParameter("content");

        // 1. pk값 이용한 엔티티 찾기
        Optional<TestEntity> optionalTestEntity = testRepository.findById(no);
            // Optional 클래스 : 제네릭 클래스의 객체를 저장 [만약에 null 이면 null 저장 ]
        
        // 2. Optional 엔티티 빼오기 [ Optional 객체 내 실제 entity 가져오기]
        TestEntity entity = optionalTestEntity.get();
        // 3. 수정
        entity.content = content;
        // 4. 반환
        return "1";

    }


}
   // @RequestMapping // 얘가 대장(상위)
        // 모든 URL 맵핑 가능


   // @GetMapping
        // Get 메소드 URL 맵핑 [매개변수(요청변수)가 보인다 : 보안 X 캐시 O]


   // @PostMapping
        // Post 메소드 URL 맵핑  [매개변수(요청변수)가 보이지 않ㅇ늠 : 보안 O 캐시 X]

//////////////////////////////////////////////// Spring 에서 지원하는 요청 방식 구분 /////////////////////////////////////////////
   // @PutMapping 
        // Put 메소드 URL 맵핑   [ 데이터의 삽입, 수정 사용 ]


   // @DeleteMapping       [삭제시 사용 ]
        // Delete 메소드 URL 맵핑핑
     // put과 delete 는 회사마다 부서마다 사용 유무 다름 : 프론트에서는 자주 사용

   // @PathVariable : 경로에 변수를 바인딩(넘겨주기)


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //  멱득성 : 요청후에 서버에 상태를 남기기 
           // Post : 상태를 남기지 않음 (멱득성 X)
           // put/delete : 멱득성 사용
    // 반복 되는 많은 요청이 있을 경우에 Post 속도가 느림! --> put, delete 사용 권장(Spring에서)
    

