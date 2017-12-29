package com.et.food.service;

import com.et.food.entity.Student;
import com.et.food.utils.PageTools;



public interface StudentService {
	/**
	 * ��ѯ����
	 * @param snameѧ������
	 * @param page ��ǰҳ
	 * @param rows ÿҳ��ʾ������
	 * @return
	 */
	public abstract PageTools queryStudent(String sname,Integer page,Integer rows);
	/**
	 * ͨ��idɾ��ѧ��
	 * @param sid ѧ��id
	 */
	public  abstract void deleteStudent(Integer sid);
	/**
	 * �޸�ѧ����Ϣ
	 * @param student ѧ������
	 */
	public abstract void updateStudent(Student student);
	/**
	 * ���ѧ����Ϣ
	 * @param student ѧ������
	 */
	public abstract void saveFood(Student student);
}