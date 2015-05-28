package com.psy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psy.entity.Teacher;
import com.psy.service.TeacherService;
import com.psy.util.StringHelper;

@Controller
@RequestMapping("/teacher.do")
public class TeacherAction {
	@Autowired
	private TeacherService teacherService;
	public TeacherAction(){
		
	}
	
	@RequestMapping
	public String load(ModelMap modelMap){
		List<Teacher> list = teacherService.getTeacherList();
		modelMap.put("list", list);
		return "teacher/teacher";
	}
	
	@RequestMapping(params = "method=add")
	public String add(HttpServletRequest request, ModelMap modelMap) throws Exception{
		return "teacher/teacher_add";
	}
	
	@RequestMapping(params = "method=save")
	public String save(HttpServletRequest request, ModelMap modelMap){
		String name = request.getParameter("name");
		String psw = request.getParameter("password");
		String sex = request.getParameter("sex");
		Teacher st = new Teacher();
		st.setName(name);
		st.setPassword(psw);
		st.setSex(sex);
		try{
			teacherService.save(st);
			modelMap.put("addstate", "添加成功");
		}
		catch(Exception e){
			modelMap.put("addstate", "添加失败");
			e.printStackTrace();
		}
		
		return "teacher/teacher_add";
	}
	
	@RequestMapping(params = "method=del")
	public void del(@RequestParam("id") String id, HttpServletResponse response){
		try{
			//获取id值
			if(!StringHelper.isEmptyObject(id)){
				teacherService.deleteTeacher(id);
			}
			response.getWriter().print("{\"del\":\"true\"}");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
