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
		//��ȡ��ǰ�û�
		Subject currentUser = SecurityUtils.getSubject();
		//�û�������û���������
		 UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		
		 try {
			    currentUser.login( token );
			    request.getRequestDispatcher("/suc.jsp").forward(request, response);
			    //����¼����û��Ƿ�ӵ��ĳ����ɫ
			    if(currentUser.hasRole("role1")){
			    	System.out.println("�Ƿ�ӵ��role1��ɫ");
			    }
			  //����¼����û��Ƿ�ӵ��ĳ��Ȩ��
			    if(currentUser.isPermitted("user:delete:1")){
			    	System.out.println("�Ƿ�ӵ�в�ѯ1�ŵ�Ȩ��");
			    }
			} catch ( UnknownAccountException uae ) {
				System.out.println("�˺Ŵ���");
				
			} catch ( IncorrectCredentialsException ice ) {
				System.out.println("���벻ƥ��");

			} catch ( LockedAccountException lae ) {
				System.out.println("�˺ű�����");

			} catch ( AuthenticationException ae ) {
				System.out.println("λ���쳣");
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
