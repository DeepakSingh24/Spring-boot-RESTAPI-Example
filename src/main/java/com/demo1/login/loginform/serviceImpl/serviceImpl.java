package com.demo1.login.loginform.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo1.login.loginform.DAOImpl.DaoImpl;
import com.demo1.login.loginform.model.Employee;
import com.demo1.login.loginform.model.ExceptionResponse;
import com.demo1.login.loginform.model.LoginMdl;
import com.demo1.login.loginform.model.Model;
import com.demo1.login.loginform.service.service;

@Service
public class serviceImpl implements service {

	@Autowired
	DaoImpl daoImpl;

	@Override
	public int createStudent(Model model) {
		return daoImpl.createStudent(model);
	}

	@Override
	public int updateStudent(Model model) {
		return daoImpl.updateStudent(model);
	}

	@Override
	public int deleteStudent(int id) {
		return daoImpl.deleteStudent(id);
	}

	@Override
	public boolean findStudent(LoginMdl loginmdl) {
		return daoImpl.findStudent(loginmdl);
	}

	@Override
	public List<Model> findAllStudent() {

		return daoImpl.findAllStudent();
	}

	public ExceptionResponse findAllException() {

		return daoImpl.findAllException();
	}

	@Override
	public Employee findEmpByDept() {
		// TODO Auto-generated method stub
		return daoImpl.findEmpByDept();
	}

}
