package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Emp2;
import com.example.demo.model.dao.Emp2Dao;

@Service
public class Emp2Service {

	@Autowired
	Emp2Dao emp2Dao;

	public List<Emp2> getAll() {
		return emp2Dao.getAll();
	}

	public Emp2 getEmpById(int id) {
		List<Emp2> emplist = emp2Dao.findByID(id);
		if (emplist.size() < 1) {
			return null;
		} else {
			Emp2 emp = emplist.get(0);
			return emp;
		}
	}
	public void addEmp(Emp2 emp) {
		emp2Dao.addEmp(emp);
	}
	public void updateEmp(Emp2 emp) {
		emp2Dao.updateEmp(emp);
	}

	public List<Emp2> getBySal(Double sal){
		return emp2Dao.findBySal(sal);
	}
}
