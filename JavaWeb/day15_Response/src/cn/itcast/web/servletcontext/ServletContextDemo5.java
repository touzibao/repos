package cn.itcast.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo5")
public class ServletContextDemo5 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取ServletContext对象
        ServletContext context = this.getServletContext();

        //调用ServletContext对象中的getRealPath获取服务器路径
        String realPathB = context.getRealPath("/b.txt");
        System.out.println(realPathB);

        String realPathC = context.getRealPath("/WEB-INF/c.txt");
        System.out.println(realPathC);

        String realPathA = context.getRealPath("/WEB-INF/classes/a.txt");
        System.out.println(realPathA);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
