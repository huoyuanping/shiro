package com.et.food.service;

import com.et.food.entity.Emp;
import com.et.food.utils.PageTools;



public interface EmpService {
	
	
	/**
	 * ��ѯ����
	 */
	public abstract PageTools queryEmp(Integer id,String ename,Integer page,Integer rows);
	/**
	 * �޸�Ա����Ϣ
	 */
	public abstract void updateEmp(Emp emp);
	/**
	 * ���Ա����Ϣ
	 */
	public abstract void saveEmp(Emp emp);
	
	/**
	 * ͨ��idɾ��Ա��
	 */
	public  abstract void deleteEmp(Integer id);
}