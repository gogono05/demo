package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Emp2;
import com.example.demo.model.service.Emp2Service;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

	@Autowired
	Emp2Service emp2Service;

	// 回傳全部emp資料
	@GetMapping("/emps")
	public List<Emp2> getAll() {
		return emp2Service.getAll();
	}

	// 查詢某筆資料
	@GetMapping("/emps/{id}")
	public ResponseEntity<Emp2> getEmp(@PathVariable("id") String empno) {
		try {
			Emp2 emp = emp2Service.getByEmpno(Integer.valueOf(empno));
			if (emp == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().body(emp);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	// 新增一筆新的emp資料
	@PostMapping("/emps")
	public ResponseEntity<?> addEmp(Emp2 emp) {
		try {
			emp2Service.addEmp(emp);
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
		return ResponseEntity.ok().build();
	}

	// 修改某筆emp資料
	@PutMapping("/emps")
	public ResponseEntity<?> updateEmp(@RequestBody Emp2 emp) {
		try {
			emp2Service.updateEmp(emp);
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
		return ResponseEntity.ok().build();
	}

	// 查詢資料從sal
	@GetMapping("/emp/sal/{sal}")
	public ResponseEntity<List<Emp2>> getEmpBySal(@PathVariable(name = "sal") String sal) {
		// return emp2Service.getAll().stream().filter(emp1 -> emp1.getSal() >=
		// sal).collect(Collectors.toList());
		try {
			Double salDouble = Double.valueOf(sal);
			return ResponseEntity.ok().body(emp2Service.getBySal(salDouble));
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	// 查詢資料從date
	@GetMapping("/emp/date/{dateStr}/{dateEnd}")
	public ResponseEntity<List<Emp2>> getEmp(@PathVariable String dateStr, @PathVariable String dateEnd) {
		try {
			LocalDate localdateStr = LocalDate.parse(dateStr);
			LocalDate localdateEnd = LocalDate.parse(dateEnd);
			if (!localdateStr.isBefore(localdateEnd)) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok().body(emp2Service.getByDate(localdateStr, localdateEnd));
		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
