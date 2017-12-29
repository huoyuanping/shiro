package com.et.shiro.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.et.shiro.entity.Menu;
import com.et.shiro.entity.UserInfo;

public interface UserMapper {
	
	@Select("select user_name as userName,pass_word as password from user_info where user_name=#{0}")
	public UserInfo queryUser(String userName);

	/**
	 * 通过名称查询角色
	 * @param userName
	 * @return
	 */
	@Select("SELECT r.role_name FROM user_info u "
			+ "INNER JOIN user_role_relation urr ON u.user_id=urr.user_id "
			+ "INNER JOIN role r ON urr.role_id=r.role_id WHERE user_name=#{0}")
	public Set<String> queryRoleByName(String userName);
	
	/**
	 * 通过名称查询角色
	 * @param userName
	 * @return
	 */
	@Select("SELECT p.perm_tag FROM user_info u INNER JOIN user_role_relation urr ON u.user_id=urr.user_id "
                + " INNER JOIN role r ON urr.role_id=r.role_id "
                + " INNER JOIN role_perm_relation rpr ON r.role_id=rpr.role_id "
                + " INNER JOIN perms p ON rpr.perm_id=p.perm_id " 
  				+ " WHERE user_name=#{0}")
	public Set<String>  queryPermByName(String userName);
	
	/**
	 * 查询菜单
	 * @return
	 */
	@Select("select menu_id as menuId,menu_name as menuName,menu_url as menuUrl,menu_filter as menuFilter,is_menu as isMenu from menu")
	public List<Menu> queryMenu();
	
	/**
	 * 通过url获取权限
	 */
	@Select("select menu_id as menuId,menu_name as menuName,menu_url as menuUrl,menu_filter as menuFilter,is_menu as isMenu from menu where menu_url=#{0}")
	public List<Menu> queryByUrl(String url);
	
	/**
	 * 通过用户获取菜单
	 */
	@Select("SELECT menu_name as menuName,menu_url as menuUrl,menu_filter as menuFilter,is_menu as isMenu FROM menu m INNER JOIN user_menu_relation umr ON m.menu_id=umr.menu_id "
			+ "INNER JOIN user_info u ON umr.user_id=u.user_id "
			+ "WHERE user_name=#{0}")
	public List<Menu> queryByUser(String userName);
}
