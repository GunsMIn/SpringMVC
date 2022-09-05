package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
* 파라미처 전송기능
* username =hello age =10*/
@WebServlet(name="requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("requrstparam.service");
        //Enumeration<String> parameterNames = request.getParameterNames(); //getparameterNames는 모든 requeset를 다 꺼낼 수 있다.

        System.out.println("start");


        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + ":" + request.getParameter(paramName)));

        System.out.println("end");

        System.out.println();

        System.out.println("단일 파라미터 조회");
        //name을 전달해주면 value가 꺼내져온다
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        //요청파라미터 key가 중복 될 시  request.getParameterValues();
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("name = " + name);
        }

        response.getWriter().write("ok");


    }
}
