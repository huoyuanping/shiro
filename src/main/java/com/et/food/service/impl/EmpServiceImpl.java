 package com.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.et.food.dao.EmpMapper;
import com.et.food.entity.Emp;
import com.et.food.entity.EmpExample;
import com.et.food.entity.EmpExample.Criteria;
import com.et.food.service.EmpService;
import com.et.food.utils.PageTools;
@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpMapper em;
	public PageTools queryEmp(Integer id,String ename,Integer page,Integer rows){
		if(ename==null){
			ename="";
		}
		
		//发起sql语句查询总记录数
		EmpExample se=new EmpExample();
		Criteria criteria = se.createCriteria();
		criteria.andEnameLike("%"+ename+"%");
		if(id!=null){
			criteria.andDeptidEqualTo(id);
		}
		int totalCount=(int)em.countByExample(se);
		PageTools pt=new PageTools(page, totalCount, rows);
		RowBounds rb=new RowBounds( pt.getStartIndex()-1, rows);
		List<Emp> list=em.selectByExampleWithRowbounds(se, rb);
		pt.setRows(list);
		return pt;
		
	}
	
	/**
	 * 获取总行数
	 */
	public int queryEmpCount(EmpExample se){
		int totalCount=(int)em.countByExample(se);
		return totalCount;
	}
	
	
	/**
	 * 修改员工信息
	 */
	public void updateEmp(Emp emp) {
		em.updateByPrimaryKey(emp);
		
	}
	/**
	 * 添加员工信息
	 */
	public void saveEmp(Emp emp) {
		em.insert(emp);
		
	}
	/**
	 * 通过id删除员工
	 */
	public void deleteEmp(Integer id) {
		em.deleteByPrimaryKey(id);
		
	}
	
	
}
