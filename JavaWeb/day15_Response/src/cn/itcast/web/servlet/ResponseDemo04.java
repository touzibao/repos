package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/demo4")
public class ResponseDemo04 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //出现中文乱码的情况
        //1，获取流之前，设置流的编码格式,但这个步骤只能设置编码格式，而不能告知浏览器
        //resp.setCharacterEncoding("gbk");

        //2，设置响应头对象，设置编码格式，并告知客户端
        //resp.setHeader("content-type","text/html;charset=utf-8");

        //3，
        resp.setContentType("text/html;charset=utf-8");
        //获取字符输出流对象，将字符输出浏览器用户端
        PrintWriter pw = resp.getWriter();
        //pw.write("response");
        pw.print("哈哈");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
