package com.et.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		//获取当前用户
		Subject currentUser = SecurityUtils.getSubject();
		//用户输入的用户名和密码
		 UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		
		 try {
			    currentUser.login( token );
			    request.getRequestDispatcher("/suc.jsp").forward(request, response);
			    //检查登录后的用户是否拥有某个角色
			    if(currentUser.hasRole("role1")){
			    	System.out.println("是否拥有role1角色");
			    }
			  //检查登录后的用户是否拥有某个权限
			    if(currentUser.isPermitted("user:delete:1")){
			    	System.out.println("是否拥有查询1号的权限");
			    }
			} catch ( UnknownAccountException uae ) {
				System.out.println("账号错误");
				
			} catch ( IncorrectCredentialsException ice ) {
				System.out.println("密码不匹配");

			} catch ( LockedAccountException lae ) {
				System.out.println("账号被锁定");

			} catch ( AuthenticationException ae ) {
				System.out.println("位置异常");
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
