package cn.itcast.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/demo6")
public class ServletDemo06 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1,通过参数名称获取参数值  即 通过键找值
        /*String password = req.getParameter("password");
        System.out.println("post...");
        System.out.println(password);*/

        //2,通过键获取多个值 多用于复选框
       /* String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }*/

       //3,查询所有的键
        /*Map<String, String[]> ParameterNames = req.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = ParameterNames.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            System.out.println(key);
            //注意：ParameterMap获取的是键值对，值是以数组形式存储的
            for (String s : value) {
                System.out.println(s);
            }
            System.out.println("---------------------------");
        }*/

        //4,获取所有的参数名称 即键
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            //System.out.println(name);
            //获取到键，name，可以调用方法getParameter
            //但getParameter不能适用于复选框
            String parameter = req.getParameter(name);
            System.out.println(parameter);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req,resp);
    }
}
