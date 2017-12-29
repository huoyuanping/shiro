package com.et.shiro.servlet;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro {
	public static void main(String[] args) {
		//��ini�����ļ��ж�ȡ�û���Ȩ����Ϣ  ����SecurityManager����
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");  
        org.apache.shiro.mgt.SecurityManager securityManager = (org.apache.shiro.mgt.SecurityManager)factory.getInstance();  
        SecurityUtils.setSecurityManager(securityManager);  
		//��ȡ��ǰ�û�
		Subject currentUser = SecurityUtils.getSubject();
		//����session ��ǰ�û��ĻỰ
		Session session = currentUser.getSession();
	   /**    
         * �û�����������   
         * principals and credentials  
         * principals�����ˣ���ʾ�û��ı�ʶ��Ϣ �����û��� �û���ַ��  
         * credentials��ƾ֤����ʾ�û����ڵ�¼��ƾ֤ �������� ֤���  
         */  
		//�ж��Ƿ��¼  δ��¼  ����Ҫ��¼
		if ( !currentUser.isAuthenticated() ){
			//�û�������û���������
			 UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
			 //���ü�ס����
			 token.setRememberMe(true);
			 
			 try {
				    currentUser.login( token );
				    System.out.println("��¼�ɹ�!");
				    System.out.println(currentUser.isAuthenticated());
				    System.out.println(currentUser.isRemembered());
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
	}
}
