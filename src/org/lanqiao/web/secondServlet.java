package org.lanqiao.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet( "/secondServlet")
public class secondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out =  response.getWriter();
       out.write("secondServlet");
        System.out.println("---------------------");
        /*//错误页面
        response.sendError(404,"您要查询的内容不存在");*/

       //验证发送的请求为同一请求(在多个servlet之间request域中的数据是可以共享的)
        out.write("data");
        System.out.println("secondServlet中username :" + request.getParameter("username"));
        //请求转发不仅可以转发到Servlet中，也可以转发到jsp页面上
        request.getRequestDispatcher("/index.jsp").forward(request,response);
       /* //5秒后自动跳转到xxx
        response.setHeader("Refresh",5,URL = "https://www.baidu.com");*/

    }
}
