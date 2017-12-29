package com.et.shiro.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.et.shiro.dao.UserMapper;

@Controller
public class LoginController {
	@Autowired
	UserMapper user;
	@RequestMapping("/loginShiro")
	public String login(String userName,String password,Model model){
		
		//��ȡ��ǰ�û�
		Subject currentUser = SecurityUtils.getSubject();
		//�û�������û���������
		 UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		
		 try {
			
			    currentUser.login( token );
			    //��ѯ�˵�
			    model.addAttribute("menuList", user.queryByUser(userName));
			   
			    return "/layout.jsp";

			} catch ( UnknownAccountException uae ) {
				System.out.println("�˺Ŵ���");
				
			} catch ( IncorrectCredentialsException ice ) {
				System.out.println("���벻ƥ��");

			} catch ( LockedAccountException lae ) {
				System.out.println("�˺ű�����");

			} catch ( AuthenticationException ae ) {
				System.out.println("λ���쳣");
			}
		 model.addAttribute("msg", "�������");
		 		return "/error.jsp";
	}
}
