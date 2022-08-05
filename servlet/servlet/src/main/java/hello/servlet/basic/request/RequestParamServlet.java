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

         request.getParameterNames().asIterator()//getparameterNames는 모든 requeset를 다 꺼낼 수 있다.
            .forEachRemaining(paramname -> System.out.println(paramname+"=" + request.getParameter(paramname)));

        System.out.println();

        System.out.println("단일 파라미터 조회");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();


    }
}
