package com.wangshuo.store.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
@WebServlet(urlPatterns = "/index")
public class IndexServlet extends BaseServlet{
    public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return "/jsp/index.jsp";
    }
}
