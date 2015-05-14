package com.psy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psy.entity.Student;
import com.psy.service.StudentService;
import com.psy.util.StringHelper;

@Controller
@RequestMapping("/student.do")
public class StudentAction {
	@Autowired
	private StudentService studentService;
	public StudentAction(){
		
	}
	
	@RequestMapping
	public String load(ModelMap modelMap){
		List<Student> list = studentService.getStudentList();
		modelMap.put("list", list);
		return "student/student";
	}
	
	@RequestMapping(params = "method=add")
	public String add(HttpServletRequest request, ModelMap modelMap) throws Exception{
		return "student/student_add";
	}
	
	@RequestMapping(params = "method=save")
	public String save(HttpServletRequest request, ModelMap modelMap){
		String user = request.getParameter("user");
		String psw = request.getParameter("psw");
		Student st = new Student();
		st.setUser(user);
		st.setPsw(psw);
		try{
			studentService.save(st);
			modelMap.put("addstate", "添加成功");
		}
		catch(Exception e){
			modelMap.put("addstate", "添加失败");
			e.printStackTrace();
		}
		
		return "student/student_add";
	}
	
	@RequestMapping(params = "method=del")
	public void del(@RequestParam("id") String id, HttpServletResponse response){
		try{
			//获取id值
			if(!StringHelper.isEmptyObject(id)){
				studentService.deleteStudent(id);
			}
			response.getWriter().print("{\"del\":\"true\"}");
			System.out.println("删除成功！");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("删除失败！");
		}
	}
}
