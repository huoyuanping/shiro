package com.et.conf;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.et.shiro.dao.UserMapper;
import com.et.shiro.entity.UserInfo;

/**
 * �Զ���realm��ʵ��
 * @author Administrator
 *ע�뵽spring����������ע��@Component ���Զ�װ��һ��bean bean����������ĸСд
 */
@Component
public class MyDbRealm extends AuthorizingRealm{
	@Autowired
	UserMapper user;
	/**
	 * ��֤
	 * ����¼������û�������������ݿ��е��û���������Ա��Ƿ����
	 * ����ֵnull ��ʾ��֤ʧ�� ��null��֤ͨ��
	 * ����sa��ʾ��¼�ɹ�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//��ȡҳ�洫���token���û��������룩
		 UsernamePasswordToken upt= (UsernamePasswordToken)token;
		 UserInfo queryUser=user.queryUser(upt.getUsername());
		
		 //�ж��û���û��
		 if(queryUser!=null && queryUser.getPassword().equals(new String(upt.getPassword()))){
			
			 //�����˺�
			 SimpleAccount sa=new SimpleAccount(upt.getUsername(),upt.getPassword(),"MyDbRealm");
			 return sa;
		 }
		return null;
	}

	/**
	 * ��ȡ��ǰ�û�����Ȩ����
	 * Ĭ�� �ڽ�����Ȩ��֤�ǵ���
	 * ���Ȩ�޵��� checkRole checkPerm
	 * @param arg0
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("��ȡȨ����Ϣ");
		//��ȡ�û���
		String userName=principals.getPrimaryPrincipal().toString();
		Set<String> roleList=user.queryRoleByName(userName);
		Set<String> permsList=user.queryPermByName(userName);
		//Ȩ�޺ͽ�ɫ����
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        authorizationInfo.setRoles(roleList);  
        authorizationInfo.setStringPermissions(permsList);  
        return authorizationInfo;  
	}

}
