package com.et.food.service;

import java.util.List;

import com.et.food.entity.TreeNode;



public interface DeptService {
	/**
	 * ��ѯ����
	 */
	public List<TreeNode> queryDept(Integer pid);
	
}