package org.lanqiao.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/fristServlet")
public class fristServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证发送的请求为同一请求(在多个servlet之间request域中的数据是可以共享的)
        request.setAttribute("data","二哈");
        System.out.println("username :" + request.getParameter("username"));
        /*//请求转发    携带参数：request，response
        request.getRequestDispatcher("/secondServlet").forward(request,response);*/
        //请求包含
        request.getRequestDispatcher("/secondServlet").include(request,response);
    }
}
