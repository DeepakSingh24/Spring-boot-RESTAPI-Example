package com.demo1.login.loginform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo1.login.loginform.model.Employee;
import com.demo1.login.loginform.model.ExceptionResponse;
import com.demo1.login.loginform.model.LoginMdl;
import com.demo1.login.loginform.model.Model;
import com.demo1.login.loginform.model.Status;
import com.demo1.login.loginform.service.service;

/**
 * @author user
 *
 */
@RestController
public class ControllerClass {
	Logger logger = LogManager.getLogger(ControllerClass.class);
	private static final int Success_code = 1;
	private static final int Error_code = 0;

	@Autowired
	service Service;

	// for student creation
	@PostMapping("/signup")
	public Status createEmployee(@RequestBody @Valid Model model, BindingResult bindingResult) {
		Status status = new Status();

		if (bindingResult.hasErrors()) {
			status.setCode(Error_code);
			status.setMessage("binding not done properly");
		}
		String sid = Integer.toString(model.getSid());
		if (!sid.matches("[0-9]+") || sid == "") {
			status.setCode(Error_code);
			status.setMessage("Please enter a valid student id");
		} else if (model.getSname() == "" || !(model.getSname().matches("[a-zA-Z]+")) || model.getSname() == null) {
			status.setCode(Error_code);
			status.setMessage("Please Enter a username");

		} else if (model.getAddress() == "" || !(model.getAddress().matches("[a-zA-Z]+"))
				|| model.getAddress() == null) {
			status.setCode(Error_code);
			status.setMessage("Please Enter a valid address");
		}
		 else if (model.getMnumber() == "" || (model.getMnumber()).length() < 10
					|| (model.getMnumber()).length() > 10) {
				status.setCode(Error_code);
				status.setMessage("Please Enter your valid password");

			}

		else {
			try {
				int rows = Service.createStudent(model);
				if (rows <= Error_code) {
					status.setCode(Error_code);
					status.setMessage("Student creation service failed");
				} else {
					status.setCode(Success_code);
					status.setMessage(
							"Student created successfully with id " + model.getSid() + " and name " + model.getSname());
				}

			} catch (Exception e) {
				status.setCode(Error_code);
				status.setMessage("Student creation failed");
			}

		}

		return status;
	}

	@PostMapping("/login")
	public Status login(@RequestBody @Valid LoginMdl loginmdl, BindingResult bindingResult) {
		Status status = new Status();
		System.out.println("a");
		System.out.println(loginmdl.getSname());
		// System.out.println(request.getParameter("pass"));

		if (loginmdl.getSname() == "" || loginmdl.getPass() == "") {
			status.setCode(Error_code);
			status.setMessage("Please Enter a username & password");
		} else if (loginmdl.getSname() == "" || !(loginmdl.getSname().matches("[a-zA-Z]+"))
				|| loginmdl.getSname() == null) {
			status.setCode(Error_code);
			status.setMessage("Please Enter a username");

		} else if (loginmdl.getPass() == "" || (loginmdl.getPass()).length() < 10
				|| (loginmdl.getPass()).length() > 10) {
			status.setCode(Error_code);
			status.setMessage("Please Enter your valid password");

		}

		else {
			try {
				boolean bool = Service.findStudent(loginmdl);
				int val = (bool) ? 1 : 0;
				if (val <= Error_code) {
					status.setCode(Error_code);
					status.setMessage("Not registered account");

				} else {
					status.setCode(Success_code);
					status.setMessage("Student is registered");
				}

			} catch (Exception e) {
				status.setCode(Error_code);
				status.setMessage("Service failed");

			}

		}

		return status;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Status login1(HttpServletRequest request, HttpServletResponse response) {
		Status status = new Status();
		// String str = request.getParameter("pass");

		System.out.println(request.getParameter("pass"));
		System.out.println(request.getParameter("id"));
//		StringBuffer jb = new StringBuffer();
//		  String line = null;
//		  try {
//		    BufferedReader reader = request.getReader();
//		    while ((line = reader.readLine()) != null)
//		      jb.append(line);
//		  } catch (Exception e) { /*report an error*/ }
//
//		  try {
//		    JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
//		  } catch (JSONException e) {
//		    // crash and burn
//		    throw new IOException("Error parsing JSON request string");
//		  }

		return status;

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Status update(@RequestBody @Valid Model model, BindingResult bindingResult) {
		Status status = new Status();

		if (bindingResult.hasErrors()) {
			status.setCode(Error_code);
			status.setMessage("binding not done properly");
		}
		
		String sid = Integer.toString(model.getSid());
		if (!sid.matches("[0-9]+") || sid == "") {
			status.setCode(Error_code);
			status.setMessage("Please enter a valid student id");
		} else if (model.getSname() == "" || !(model.getSname().matches("[a-zA-Z]+")) || model.getSname() == null) {
			status.setCode(Error_code);
			status.setMessage("Please Enter a username");

		} else if (model.getAddress() == "" || !(model.getAddress().matches("[a-zA-Z]+"))
				|| model.getAddress() == null) {
			status.setCode(Error_code);
			status.setMessage("Please Enter a valid address");
		}
		 else if (model.getMnumber() == "" || (model.getMnumber()).length() < 10
					|| (model.getMnumber()).length() > 10) {
				status.setCode(Error_code);
				status.setMessage("Please Enter your valid password");

			}


		 else {
			 try {
					int m = Service.updateStudent(model);

					if (m <= Error_code) {
						status.setCode(Error_code);
						status.setMessage("Student update Service failed");
					} else {
						status.setCode(Success_code);
						status.setMessage("Student updated Successfully");
					}

				} catch (Exception e) {
					System.out.println(e);

				}
		 }

		return status;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Status delete(@PathVariable int id) {
		Status status = new Status();
		String sid = Integer.toString(id);
		
		if(!sid.matches("[0-9]+") || sid == "") {
			status.setCode(Error_code);
			status.setMessage("Please enter a valid student id");

		}

		else {
			try {
				int cnt = Service.deleteStudent(id);

				if (cnt <= Error_code) {
					status.setCode(Error_code);
					status.setMessage("Student is not deleted");
				} else {
					status.setCode(Success_code);
					status.setMessage("Student is deleted");

				}
			} catch (Exception e) {
				System.out.println(e);

			}
		}

		return status;
	}

	@RequestMapping(value = "/searchAll", method = RequestMethod.GET)
	public List<Model> SearchAll() {
		List<Model> list = Service.findAllStudent();
		return list;
	}

	@RequestMapping(value = "/searchException", method = RequestMethod.GET)
	public ExceptionResponse searchException() {
		// List<ExceptionResponse> list1=null;
		ExceptionResponse list1 = null;
		try {
			list1 = Service.findAllException();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list1;
	}

	@RequestMapping(value = "/searchEmployee", method = RequestMethod.GET)
	public Employee searchByDept() {

		Employee emp = new Employee();
		try {
			emp = Service.findEmpByDept();
		} catch (Exception e) {
			System.out.println("**** Error throwing from controller");
		}
		return emp;

	}

}
