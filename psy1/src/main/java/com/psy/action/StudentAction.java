package com.psy.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.psy.entity.Student;
import com.psy.service.StudentService;
import com.psy.util.HibernateWebUtils;
import com.psy.util.PropertyFilter;

@Results({
	@Result(name="list",location="student/student.jsp"),
	@Result(name="view",location="address/address-view.jsp"),
	@Result(name="input",location="address/address-input.jsp"),
	@Result(name="success",location="student.do",type="redirect")
})
public class StudentAction extends CrudActionSupport<Student>{
	/** 
	 *  serialVersionUID  
	 *  TODO(用一句换描述这个变量的作用)
	*/
	@Autowired
	private StudentService studentService;

	@Override
	public Student getModel() {
		return null;
	}

	@Override
	public String list()  {
		pager = this.getPage();
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(getRequest());
		savePage(studentService.searchStudent(pager, filters));
		return "list";
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
	}

}
