package com.example.demo.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		Emp2 emp = null;
		try {
			emp = emp2Service.getByEmpno(Integer.valueOf(empno));
			if (emp == null) {
				return ResponseEntity.notFound().build();
			}
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().body(emp);
	}

	// 新增一筆新的emp資料
	@PostMapping("/emps")
	public ResponseEntity<?> addEmp(@RequestBody Emp2 emp) {
		emp2Service.addEmp(emp);
		return ResponseEntity.ok().build();
	}

	// 修改某筆emp資料
	@PutMapping("/emps")
	public ResponseEntity<?> updateEmp(@RequestBody Emp2 emp) {
		emp2Service.updateEmp(emp);
		return ResponseEntity.ok().build();
	}

	// 查詢資料從sal
	@GetMapping("/emp/sal/{sal}")
	public ResponseEntity<List<Emp2>> getEmpBySal(@PathVariable(name = "sal") String sal) {
		//return emp2Service.getAll().stream().filter(emp1 -> emp1.getSal() >= sal).collect(Collectors.toList());
		Double salDouble = null;
		try {
			 salDouble= Double.valueOf(sal);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(emp2Service.getBySal(salDouble));
	}

	// 查詢資料從date
	@GetMapping("/emp/date/{dateStr}/{dateEnd}")
	public ResponseEntity<List<Emp2>> getEmp(@PathVariable String dateStr, @PathVariable String dateEnd)  {
		LocalDate localdateStr;
		LocalDate localdateEnd;
		try {
			 localdateStr = LocalDate.parse(dateStr);
			 localdateEnd = LocalDate.parse(dateEnd);
			Period period = localdateStr.until(localdateEnd);
			if(period.getDays()<0) {
				return ResponseEntity.badRequest().build();
			}
		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(emp2Service.getByDate(localdateStr, localdateEnd));
	}

}
