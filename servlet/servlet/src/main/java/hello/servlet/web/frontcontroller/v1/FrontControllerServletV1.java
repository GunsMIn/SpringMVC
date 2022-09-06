package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.crypto.spec.PSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//프론트 컨틀롤러는 서블릿이어야하고 서블릿의 역할을 한다.     front-controller/v1/ 하위의 모든 url이 매핑이된다
@WebServlet(name="frontControllerServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    //key는 url울 넣어줄 것
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    //그러면 이 클래스가 처음에 생성이 될때 이값들을 넣어두게된다.
    public FrontControllerServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(" i'm 프론트컨트롤러야 ");

        //url를 받을 수 있다
        String requestURI = request.getRequestURI();

        ControllerV1 controller = controllerMap.get(requestURI);

        //controller가 값이 없을때
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);
    }
}


