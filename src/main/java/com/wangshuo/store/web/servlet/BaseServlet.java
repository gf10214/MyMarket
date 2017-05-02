package com.wangshuo.store.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by wangshuo on 2017/4/28 0028.
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class clazz=this.getClass();
            String m=req.getParameter("method");
            if(m==null){
                m="index";
            }
            Method method=clazz.getMethod(m,HttpServletRequest.class,HttpServletResponse.class);
            String s=(String) method.invoke(this,req,resp);
            if(s!=null){
                req.getRequestDispatcher(s).forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
