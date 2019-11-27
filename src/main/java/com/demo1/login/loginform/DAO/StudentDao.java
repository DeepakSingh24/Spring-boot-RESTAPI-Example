package com.demo1.login.loginform.DAO;

import java.util.List;

import com.demo1.login.loginform.model.Employee;
import com.demo1.login.loginform.model.ExceptionResponse;
import com.demo1.login.loginform.model.LoginMdl;
import com.demo1.login.loginform.model.Model;

public interface StudentDao {

	int createStudent(Model model);

	int updateStudent(Model model);

	int deleteStudent(int sid);

	boolean findStudent(LoginMdl loginmdl);

	public List<Model> findAllStudent();

	public ExceptionResponse findAllException();
	
	public Employee findEmpByDept();
}
