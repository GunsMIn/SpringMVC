# SpringMVC
Spring MVC패턴과 요청,응답방식과 HTTP API  
# ✨SPRING_STUDY
✔스프링 학습 관련 레파지토리입니다.✔
<hr>
<b>패키지 이름 내용</b><br>
- servlet : servlet, jsp동작 방식에 대한 학습<br>
- springmvc : Model, view, controller에 대한 학습<br>
- item-service : MVC패턴으로 만든 웹서비스<br>
<b>🎈학습 내용</b><br>
<b>패키지명 :servlet</b><br><br>
<b>-day 1</b>: get,post, http api의 요청과 응답에 대해 학습하였다. 또한 추후에 spring에 관하여 이해가 더 쉽게 현재 서블릿과 jsp의 동작원리를 학습중이다
        서블릿으로 개발을 하면서 중복되는 코드들이 많고 테스트 케이스의 제작 어려움 또한 기능이 복잡해질수록 공통으로 처리해야하는 부분들이 많아질 것이다.
        다음 부분에서는 그것을 해결할 <수문장> 역할인 프론트 컨트롤러 패턴을 도입할 것이다. <br><br>
<b>-day 2</b>: 프론트 컨틀롤러 패턴의 도입! <br>
        프론트컨트롤러는 수문장의 역할이다 프론트컨트롤러가 하나의 servlet의 역할을하고 controller는 비지니스 로직, model, view로이동의 역할을한다.
        점진적으로 v1~v5패키지는 mvc패턴의 발전과정과 스프링의 동작방식이 추가되는 업그레이드 버전이다 (1 -> 5 업그레이드)<br>
        v1 : 하나의 컨트롤러에서 model, view(물리적이름)로이동의 기능이 다같이 이루어지는 컨트롤러<br>
        v2 : 뷰 리졸버를 통해서 뷰의 논리 이름을 반환하게 하였다. modelView객체를 직접만들어 model과 view의 이름을 반환하게하였다
             단점 : 아직 컨트롤러가 servlet의 역할도 하고있다.<br>
        v3 : 프론트 컨트롤러를 제작함으로써 컨트롤러를 서블릿의 기능을 완전 분리. 또한 뷰 경로의 중복을 제거하였다.<br>
        v4 : 기존 구조에서 모델을 파라미터로 넘기고, 뷰의 논리이름을 반환한다는 작은 아이디어를 적용했을 뿐인데, 컨트롤러를 구현하는 개발자 입장에서 보면 이제
             군더더기 없는 코드를 작성할 수 있다.<br>
        v5 : 지금까지 개발한것은 한가지 방식의 컨트롤러 인터페이스만 사용이 가능했다.<br>
             이 점을 보완하기위해 어댑터 패턴을 도입했다. 핸들러 어댑터는 말그대로 인터페이스이다. 핸들러는 컨트롤러의 개념에서 확장한 것이다.<br><br>
<b>-day 3</b>: 디스패처 서블릿 도입! <br>
        @Controller : 스프링이 자동으로 스프링 빈으로 등록한다. (내부에 @Component 애노테이션이 있어서 컴포넌트
        스캔의 대상이 됨)
        스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.<br>
        @RequestMapping : 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다. 애노테이션을
        기반으로 동작하기 때문에, 메서드의 이름은 임의로 지으면 된다.<br>
        ModelAndView : 모델과 뷰 정보를 담아서 반환하면 된다.<br><br>
<b>패키지명 :springMVC</b><br><br>
<b>-day 4</b>: 스프링 MVC 도입과 기본기능! <br>
        @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
        @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다
        @RequestHeader MultiValueMap<String, String> headerMap
        모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다
        @RequestParam 애노테이션을 생략하면 스프링 MVC는 내부에서 required=false 를 적용한다.
        스프링MVC는 @ModelAttribute 가 있으면 다음을 실행한다.
        HelloData 객체를 생성한다.
        요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를
        호출해서 파라미터의 값을 입력(바인딩) 한다.
        @RequestBody 를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회할 수 있다.
        메세지 컨버터로 JSON으로 받는것을 객체로 또는 STRING으로 변환해서 받을 수 있다.<br<br><br><br>
<b>패키지명 :item-service</b><br><br>
<b>-day 5</b>: 스프링 MVC로 간단한 서비스 제작하기 <br>
        1.@ModelAttribute - 요청 파라미터 처리
        @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로 입력해준다.<br>
        2.@ModelAttribute - Model 추가
        @ModelAttribute 는 중요한 한가지 기능이 더 있는데, 바로 모델(Model)에 @ModelAttribute 로
        지정한 객체를 자동으로 넣어준다. 지금 코드를 보면 model.addAttribute("item", item) 가 주석처리
        되어 있어도 잘 동작하는 것을 확인할 수 있다.<br>
        3.PRG Post/Redirect/Get : 새로고침하면 마지막에 수행한 동작을 다시 수행하게 되는데 post 방식에서는 이를 방지하기 위해
        redirect를 통해 get방식으로 다시 요청하게 만들어주었다
        
<br><br><b>🎈학습에 대하여 느낌점</b><br>
MVC패턴의 탄생배경과(그 전의 방식은 너무 번거롭다) 동작 방식에 대하여 알게되었다. 그리고 컨틀롤러 매핑의 종류와 각각의 매핑에대하여 더 상세하게 알게되었고<br>
세세하게 동작원리를 알아가다 보니 redirect방법에 대해서도 이해하게되었다
