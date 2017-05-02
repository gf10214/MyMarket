package com.wangshuo.store.web.servlet;

import com.mchange.v2.beans.BeansUtils;
import com.wangshuo.store.contant.Constant;
import com.wangshuo.store.domain.User;
import com.wangshuo.store.myconventer.Myconventer;
import com.wangshuo.store.service.UserService;
import com.wangshuo.store.service.impl.UserServiceImpl;
import com.wangshuo.store.utils.MD5Utils;
import com.wangshuo.store.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
@WebServlet(urlPatterns="/user")
public class UserServlet extends BaseServlet {

     public String add(HttpServletRequest request, HttpServletResponse response){
        System.out.println("userServlet add 方法执行了");
        return null;
     }

    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @return
     */
    public String registUI(HttpServletRequest request, HttpServletResponse response){
        return "/jsp/register.jsp";
    }
    public String loginUI(HttpServletRequest request, HttpServletResponse response){
        return "/jsp/login.jsp";
    }
    public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1封装数据
        User user=new User();
        //注册自定义转换器
        ConvertUtils.register(new Myconventer(), Date.class);
        BeanUtils.populate(user,request.getParameterMap());
        //1.1设置用户id
        user.setUid(UUIDUtils.getId());
        //1.2设置激活码
        user.setCode(UUIDUtils.getCode());
        //1.3加密密码
        user.setPassword(MD5Utils.md5(user.getPassword()));
        //2调用servcie完成注册
        UserService s=new UserServiceImpl();
        s.regist(user);
        //3.请求转发
        request.setAttribute("msg","注册已成功，请去邮箱激活");
        return "/jsp/msg.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code=request.getParameter("code");
        UserService s=new UserServiceImpl();
        User user=s.active(code);
        if(user==null){
            request.setAttribute("msg","激活失败");
        }else{
            request.setAttribute("msg","激活成功");
        }
        return "/jsp/msg.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        password=MD5Utils.md5(password);
        UserService s=new UserServiceImpl();
        User user=s.login(username,password);
        if(user==null){
            //用户名密码不匹配
            request.setAttribute("msg","用户名密码不匹配");
            return "/jsp/login.jsp";
        }else {
            if(Constant.USER_IS_ACTIVE!=user.getState()){
                request.setAttribute("msg","用户未激活");
                return "/jsp/login.jsp";
            }
        }
        request.getSession().setAttribute("user",user);
        response.sendRedirect(request.getContextPath()+"/");
        return null;
    }
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //干掉session
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
        return null;
    }
}
