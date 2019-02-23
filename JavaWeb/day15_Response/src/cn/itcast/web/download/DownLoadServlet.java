package cn.itcast.web.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;


@WebServlet("/downLoadServlet")
public class DownLoadServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* 2. 创建DownLoadServlet类，将需要的文件加载进内存
        3. 想要每次下载数据都弹出下载提示框，需要使用到相应头
        4. 使用字节输出流对象将数据输出到用户端*/

        //创建DownLoadServlet类，将需要的文件加载进内存
        //1,获取用户请求的参数值
        String filename = req.getParameter("filename");
        //2,想要将用户请求的filemane文件加载进内存中
        //2.1需要查找到文件的绝对路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/avi/"+filename);
        //2.1通过获取的绝对路径将文件加载进内存，使用inputstream
        FileInputStream fis = new FileInputStream(realPath);

        //3,想要每次下载数据都弹出下载提示框，需要使用到相应响应头
        //Content-Type
        //设置Content-Type需要使用到mime文件
        String mimeType = servletContext.getMimeType(filename);
        resp.setHeader("content-Type",mimeType);

        //想要解决中文乱码的问题
        //a，获取浏览器的编码格式agent响应头
        /*String agent = req.getHeader("user-agent");
        //b,传递给工具类，返回和每个服务器兼容的filename
        filename = DownLoadUtils.getFileName(agent, filename);*/

        //Content-disposition
        resp.setHeader("content-disposition","attachment;filename="+filename);


        //4,将文件输出给用户端，使用servlet中的方法获取输出流对象
        ServletOutputStream sos = resp.getOutputStream();
        byte[] buffer = new byte[1024*8];
        int len = 0;
        while((len = fis.read(buffer))!=-1){
            sos.write(buffer,0,len);
        }

        fis.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
