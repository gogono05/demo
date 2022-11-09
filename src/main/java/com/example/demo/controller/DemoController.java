package com.example.demo.controller;

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
@RequestMapping(value = "/emp",produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

	@Autowired
	Emp2Service emp2Service;

	//回傳全部emp資料
	@GetMapping("/getAll")
	public List<Emp2> getAll(){
		return emp2Service.getAll();
	}
	
	//查詢某筆資料
	@GetMapping("/inquireEmpno/{no}")
	public ResponseEntity<Emp2>  getemp(@PathVariable Integer no) {
		Emp2 emp = emp2Service.getEmpById(no);
		if(emp==null) { 
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	//新增一筆新的emp資料
	@PostMapping("/addemp")
	public String addEmp(@RequestBody Emp2 emp)  {
		emp2Service.addEmp(emp);
		return "addok";
	}
	
	//修改某筆emp資料
	@PutMapping("/updateemp")
	public String updateEmp(@RequestBody Emp2 emp)  {
		Emp2 oldEmp =emp2Service.getEmpById(emp.getEmpno());
		oldEmp.setEname(emp.getEname());
		oldEmp.setJob(emp.getJob());
		oldEmp.setHiredate(emp.getHiredate());
		oldEmp.setSal(emp.getSal());
		oldEmp.setComm(emp.getComm());
		emp2Service.updateEmp(emp);
		return "updateOK";
	}

	//查詢某筆資料
		@GetMapping("/inquireSal/{sal}")
		public List<Emp2> getemp(@PathVariable Double sal) {
			return emp2Service.getBySal(sal);
		}


}
