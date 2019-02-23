package cn.itcast.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo8")
public class ServletDemo08 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo8...");
        /*1. 通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(String path)
		2. 使用RequestDispatcher对象来进行转发：
		forward(ServletRequest request, ServletResponse response)*/
        req.setAttribute("hello","world");
        //获取转发器对象
        req.getRequestDispatcher("/demo9").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req,resp);
    }
}
