package hello.servlet.web.servletmvc;

import hello.servlet.basic.HelloServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HelloServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //이것의 현재 목적은 view단으로만 이동시켜주는 것
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //getRequestDispatcher() : 컨트롤러에서 view로 이동할 때 사용하는것
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
        //dispatcher.forward() : 다른 서블릿이나 JSP로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이
        //발생한다.
    }
}
