package hello.servlet.basic.response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="reaponseHeaderServlet" , urlPatterns = "/response-header")
public class ResponseHeaderServlet  extends HttpServlet {
}
