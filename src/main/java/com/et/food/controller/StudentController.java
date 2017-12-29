package com.et.food.controller;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.et.food.entity.Result;
import com.et.food.entity.Student;
import com.et.food.service.StudentService;
import com.et.food.utils.PageTools;




@Controller
public class StudentController {
	@Autowired
	StudentService service;
	/**
	 * 查询的方法
	 * @param sname学生姓名
	 * @param page 当前页
	 * @param rows 每页显示多少行
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryStudent")
	public PageTools queryStudent(String sname,Integer page,Integer rows){
		return service.queryStudent(sname,page,rows);
	}
	
	/**
	 * 删除学生
	 */
	@ResponseBody
	@RequestMapping(value="/deleteStudent/{sid}",method=RequestMethod.DELETE)
	public Result deleteStudent(@PathVariable String sid,Integer page,Integer rows){
	/*	//删除一行
		Result rt=new Result();
		rt.setCode(1);
		try {
			//检测异常
			//String str=null;
			//str.toString();
			service.deleteStudent(Integer.parseInt(sid));
		} catch (Exception e) {
			rt.setCode(0);
			rt.setMessage(e);
		}
		return rt;*/
		
		// 删除多行
		Result rt = new Result();
		rt.setCode(1);
		
		String[] str=sid.split(",");
		
		try {
			//检测异常
			/*String str=null;
			str.toString();*/
			for(int i=0;i<str.length;i++){
				//删除
				service.deleteStudent(Integer.parseInt(str[i]));
			}
		} catch (Exception e) {
			//删除失败
			rt.setCode(0);
			rt.setMessage(e);		
		}
		
		return rt;
	
	}
	
	
	/**
	 * 修改学生信息
	 */
	@ResponseBody
	@RequestMapping(value="/updateStudent/{sid}",method=RequestMethod.PUT)
	public Result updateStudent(@PathVariable Integer sid,Student student,Integer page,Integer rows){
	
		student.setSid(sid);
		Result rt=new Result();
		rt.setCode(1);
		try {
			service.updateStudent(student);
		} catch (Exception e) {
			rt.setCode(0);
			rt.setMessage(e);
		}
		return rt;
	}
	
	/**
	 *添加学生信息
	 */
	@ResponseBody
	@RequestMapping(value="/saveStudent",method=RequestMethod.POST)
	public Result saveStudent(Student student,MultipartFile  myImage){
		Result rt=new Result();
		rt.setCode(1);
		try {
			//获取文件名
			String fileName=myImage.getOriginalFilename();
			File destFile=new File("E:\\myImages\\"+fileName);
			myImage.transferTo(destFile);
			service.saveFood(student);
		} catch (Exception e) {
			rt.setCode(0);
			rt.setMessage(e);
		}
		return rt;
	}
}
