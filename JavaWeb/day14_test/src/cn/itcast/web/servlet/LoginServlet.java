package cn.itcast.web.servlet;

import cn.itcast.Dao.UserDao;
import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1,设置编码格式
        req.setCharacterEncoding("utf-8");
        //2,获取用户输入的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //3,将两个数据封装成一个User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        //调用UserDao中的login方法,将loginUser对象传递进去
        User user = UserDao.login(loginUser);

        //判断user是否为空
        //如果不是空,跳转到SuccessServlet
        if(user != null){
            //共享数据
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }else{
            //如果为空,那么跳转到failServlet
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
