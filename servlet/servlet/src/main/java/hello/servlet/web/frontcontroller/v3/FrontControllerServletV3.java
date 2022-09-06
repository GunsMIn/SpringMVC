package hello.servlet.web.frontcontroller.v3;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //URL들어감
        String requestURI = request.getRequestURI();

        //URL에 맞는 CONTROLLER 호출
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            //null일시 404뜨게함
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //여기서 request가 parmMap으로 들어옴
        Map<String, String> paramMap = createParamMap(request);
        //수행하면 ModelView 반환
        ModelView mv = controller.process(paramMap);


        String viewName = mv.getViewName(); //view의 논리이름만 뽑아온것

        //여기서 논리적 이름을 물리적 이름으로 바꾸어줌
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);

    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        //parameter들의 key값으로 paramMap에 넣어줌
        request.getParameterNames().asIterator()            //key      value
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}