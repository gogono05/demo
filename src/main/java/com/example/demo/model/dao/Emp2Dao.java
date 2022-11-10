package com.example.demo.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Emp2;

@Repository
public class Emp2Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Emp2> getAll() {
		final String SQL = "SELECT * FROM emp2";
		return jdbcTemplate.query(SQL, new RowMapper<Emp2>() {
			@Override
			public Emp2 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp2 emp = new Emp2();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				return emp;
			}
		});
	}

	public List<Emp2> findByNo(int empno) {
		final String SQL = "SELECT * FROM emp2 WHERE empno=?";
		return jdbcTemplate.query(SQL, new RowMapper<Emp2>() {
			@Override
			public Emp2 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp2 emp = new Emp2();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				return emp;
			}
		}, empno);
	}
	
	public List<Emp2> findBySal(Double sal) {
		final String SQL = "SELECT * FROM emp2 WHERE sal>=?";
		return jdbcTemplate.query(SQL, new RowMapper<Emp2>() {
			@Override
			public Emp2 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp2 emp = new Emp2();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				return emp;
			}
		}, sal);
	}
	
	public List<Emp2> findByDate(LocalDate localdate1,LocalDate localdate2) {
		final String SQL = "SELECT * FROM emp2 WHERE hiredate BETWEEN ? AND ?";
		return jdbcTemplate.query(SQL, new RowMapper<Emp2>() {
			@Override
			public Emp2 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp2 emp = new Emp2();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				return emp;
			}
		}, localdate1,localdate2);
	}

	public void addEmp(Emp2 emp) {
		final String SQL = "INSERT INTO emp2(ename,job,hiredate,sal,comm) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(SQL,
				new Object[] { emp.getEname(), emp.getJob(), emp.getHiredate(), emp.getSal(), emp.getComm() });
	}
	
	public void updateEmp(Emp2 emp) {
		final String SQL = "UPDATE emp2 SET ename = ?, job = ?, hiredate = ?, sal = ?, comm = ? WHERE empno = ?";
		jdbcTemplate.update(SQL,
				 emp.getEname(), emp.getJob(), emp.getHiredate(), emp.getSal(), emp.getComm(),emp.getEmpno());
	}
}
