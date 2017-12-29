 package com.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.et.food.dao.StudentMapper;
import com.et.food.entity.Student;
import com.et.food.entity.StudentExample;
import com.et.food.service.StudentService;
import com.et.food.utils.PageTools;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentMapper sm;
	/**
	 * 查询学生
	 */
	public PageTools queryStudent(String sname,Integer page,Integer rows){
		if(sname==null){
			sname="";
		}
		//发起sql语句查询总记录数
		StudentExample se=new StudentExample();
		se.createCriteria().andSnameLike("%"+sname+"%");
		int totalCount=(int)sm.countByExample(se);
		PageTools pt=new PageTools(page, totalCount, rows);
		RowBounds rb=new RowBounds( pt.getStartIndex()-1, rows);
		List<Student> list=sm.selectByExampleWithRowbounds(se, rb);
		pt.setRows(list);
		return pt;
		
	}
	
	/**
	 * 获取总行数
	 */
	public int queryStudentCount(StudentExample se){
		int totalCount=(int)sm.countByExample(se);
		return totalCount;
	}
	/**
	 *通过id删除学生
	 */
	public void deleteStudent(Integer sid) {
		sm.deleteByPrimaryKey(sid);
	}

	
	/**
	 * 修改学生信息
	 */
	public void updateStudent(Student student) {
		sm.updateByPrimaryKey(student);
	}
	/**
	 * 添加学生信息
	 */
	public void saveFood(Student student) {
		sm.insert(student);
	}
	
}
