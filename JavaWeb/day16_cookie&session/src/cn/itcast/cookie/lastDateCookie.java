package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/lastDateServlet")
public class lastDateCookie extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*1， request获取所有cookie，判断cookie是否为空，长度
			2， 如果存在cookie，遍历所有的cookie，判断是否存在名为LastTime的cookie
			3， 如果存在LastTime，获取值，即上次的时间，响应： 欢迎回来，您上次访问时间为:显示时间字符串
			4， 如果不存在指定的cookie，获取时间，设置cookie并响应给用户端，并响应： 您好，欢迎您首次访问。*/

        //设置编码格式
        resp.setContentType("text/html;charset=utf-8");
        //1， request获取所有cookie，判断cookie是否为空，长度
        Cookie[] cookies = req.getCookies();
        //如果不为空则遍历cookies

        //初始化lastDate不存在
        int flag = 0;
        //2， 如果存在cookie，遍历所有的cookie，判断是否存在名为LastTime的cookie
        if(cookies!=null && cookies.length!=0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                //判断是否存在lastDate这个cookie
                if("lastDate".equals(name)){
                    flag = 1;
                    //3， 如果存在LastTime，获取值，即上次的时间，响应： 欢迎回来，您上次访问时间为:显示时间字符串
                    //如果存在获取上次登录的时间Date
                    String value = cookie.getValue();
                    //URL解码
                    String decode = URLDecoder.decode(value, "utf-8");
                    resp.getWriter().write("欢迎回来，您上次访问时间为:"+decode);

                    //获取本次登录的时间
                    Date date = new Date();
                    //指定日期格式
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String date_sdf = sdf.format(date);
                    //将本次的时间设置进lastDate中
                    //URL编码
                    String encode = URLEncoder.encode(date_sdf, "utf-8");
                    cookie.setValue(encode);
                    //设置cookie的存在时间
                    cookie.setMaxAge(60*60*24*30);
                    //响应头
                    resp.addCookie(cookie);
                    break;
                }
            }
        }

        //4， 如果不存在指定的cookie，获取时间，设置cookie并响应给用户端，并响应： 您好，欢迎您首次访问。
        //如果cookie为空，或者长度为0，或者不存在名为lastDate的cookie
        if (cookies==null||cookies.length==0||flag==0){
            //创建名为lastDate的cookie,将时间和名字传递进去
            Date date = new Date();
            //指定日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String date_sdf = sdf.format(date);
            String encode = URLEncoder.encode(date_sdf, "utf-8");
            Cookie cookie = new Cookie("lastDate",encode);

            //设置cookie的存在时间
            cookie.setMaxAge(60*60*24*30);
            resp.addCookie(cookie);
            resp.getWriter().write("您好，欢迎您首次访问");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
