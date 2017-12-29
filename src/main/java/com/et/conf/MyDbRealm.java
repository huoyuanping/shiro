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
 * 自定义realm的实现
 * @author Administrator
 *注入到spring的容器中用注解@Component 会自动装配一个bean bean的名字首字母小写
 */
@Component
public class MyDbRealm extends AuthorizingRealm{
	@Autowired
	UserMapper user;
	/**
	 * 认证
	 * 将登录输入的用户名和密码和数据库中的用户名和密码对比是否相等
	 * 返回值null 表示认证失败 非null认证通过
	 * 返回sa表示登录成功
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取页面传入的token（用户名和密码）
		 UsernamePasswordToken upt= (UsernamePasswordToken)token;
		 UserInfo queryUser=user.queryUser(upt.getUsername());
		
		 //判断用户有没有
		 if(queryUser!=null && queryUser.getPassword().equals(new String(upt.getPassword()))){
			
			 //创建账号
			 SimpleAccount sa=new SimpleAccount(upt.getUsername(),upt.getPassword(),"MyDbRealm");
			 return sa;
		 }
		return null;
	}

	/**
	 * 获取当前用户的授权数据
	 * 默认 在进行授权认证是调用
	 * 检查权限调用 checkRole checkPerm
	 * @param arg0
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("获取权限信息");
		//获取用户名
		String userName=principals.getPrimaryPrincipal().toString();
		Set<String> roleList=user.queryRoleByName(userName);
		Set<String> permsList=user.queryPermByName(userName);
		//权限和角色对象
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        authorizationInfo.setRoles(roleList);  
        authorizationInfo.setStringPermissions(permsList);  
        return authorizationInfo;  
	}

}
