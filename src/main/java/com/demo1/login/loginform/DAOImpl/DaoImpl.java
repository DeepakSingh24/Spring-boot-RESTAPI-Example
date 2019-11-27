package com.demo1.login.loginform.DAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.demo1.login.loginform.DAO.StudentDao;
import com.demo1.login.loginform.model.Accounting;
import com.demo1.login.loginform.model.CustomException;
import com.demo1.login.loginform.model.Employee;
import com.demo1.login.loginform.model.ExceptionResponse;
import com.demo1.login.loginform.model.LoginMdl;
import com.demo1.login.loginform.model.Message;
import com.demo1.login.loginform.model.Model;
import com.demo1.login.loginform.model.Sales;

@Repository
public class DaoImpl implements StudentDao {
	private static final Logger LOGGER = LogManager.getLogger(DaoImpl.class);
	private static final int Row = 0;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int createStudent(Model model) {
		int rows = Row;
		try {
			rows = jdbcTemplate.update(
					"INSERT INTO \"public\".studenttable(sid,sname,saddress,smnumber) VALUES(?,?,?,?)", model.getSid(),
					model.getSname(), model.getAddress(), model.getMnumber());
		} catch (DataAccessException e) {
			LOGGER.error("Error occured" + e);

		} catch (Exception e) {
			LOGGER.error("Error occured" + e);

		}
		return rows;
	}

	@Override
	public boolean findStudent(LoginMdl loginMdl) {
		boolean result = false;
		int rows = Row;
		try {
			Model mdl = jdbcTemplate.queryForObject("select * from \"public\".studenttable where sname=? and smnumber=?",
					new Object[] { loginMdl.getSname(), loginMdl.getPass() },
					new BeanPropertyRowMapper<Model>(Model.class));

			rows = jdbcTemplate.queryForObject(
					"select count(*) from \"public\".studenttable where sname=? and smnumber=?",
					new Object[] { loginMdl.getSname(), loginMdl.getPass() }, Integer.class);
			if (rows == 1) {
				result = true;
			}

		} catch (DataAccessException e) {
			LOGGER.error("Error occured" + e);

		} catch (Exception e) {
			LOGGER.error("Error occured" + e);
		}
		return result;
	}

	@Override
	public List<Model> findAllStudent() {
		return jdbcTemplate.query("select * from \"public\".studenttable",
				new BeanPropertyRowMapper<Model>(Model.class));
	}

	@Override
	public int updateStudent(Model model) {
		final String UPDATE_QUERY = "update \"public\".studenttable set saddress = ? where sid = ?";
		return jdbcTemplate.update(UPDATE_QUERY, model.getAddress(), model.getSid());

	}

	@Override
	public int deleteStudent(int sid) {
		return jdbcTemplate.update("delete from \"public\".studenttable where sid=?", new Object[] { sid });

	}

	@Override
	public ExceptionResponse findAllException() {
		// TODO Auto-generated method stub
		try {
			return jdbcTemplate.query("select * from \"public\".exceptionresponse Limit 1",
					new ResultSetExtractor<ExceptionResponse>() {

						@Override
						public ExceptionResponse extractData(ResultSet rs) throws SQLException, DataAccessException {
							if (rs.next()) {
								ExceptionResponse res = new ExceptionResponse();

								CustomException custom = new CustomException();
								custom.setName(rs.getString("name"));

								Message m = new Message();
								m.setCategory(rs.getString("category"));
								m.setComment(rs.getString("comment"));
								m.setId(rs.getString("id"));
								custom.setMessage(m);
								custom.setDetails(rs.getString("details"));

								res.setException(custom);

								return res;
							} else {
								return null;
							}
						}

					});
		} catch (Exception e) {
			System.out.println("******************************");
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Employee findEmpByDept() {

		String param1 = "accounting";
		String param2 = "sales";
		Employee emp = new Employee();

		try {
			List<Accounting> accountList = jdbcTemplate.query("select * from \"public\".emp1 where dept=? ",
					new Object[] { param1 }, new ResultSetExtractor<List<Accounting>>() {
						@Override
						public List<Accounting> extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<Accounting> accountDataList = new ArrayList<Accounting>();
							while (rs.next()) {
								Accounting accounting = new Accounting();
								accounting.setAge(rs.getInt("age"));
								accounting.setFirstName(rs.getString("firstname"));
								accounting.setLastName(rs.getString("lastname"));

								accountDataList.add(accounting);
							}
							return accountDataList;
						}
					});
			emp.setAccounting(accountList);

			List<Sales> salesList = jdbcTemplate.query("select * from \"public\".emp1 where dept=? ",
					new Object[] { param2 }, new ResultSetExtractor<List<Sales>>() {

						@Override
						public List<Sales> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Sales> salesDataList = new ArrayList<Sales>();

							while (rs.next()) {
								Sales sales = new Sales();
								sales.setAge(rs.getInt("age"));
								sales.setFirstName(rs.getString("firstname"));
								sales.setLastName(rs.getString("lastname"));

								salesDataList.add(sales);

							}
							return salesDataList;
						}
					});

			emp.setSales(salesList);

		} catch (Exception e) {

		}
		return emp;
	}

}
