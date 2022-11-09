//package com.example.demo.controller;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.demo.model.Emp2;
//import com.example.demo.model.service.Emp2Service;
//
//@Controller
//@RequestMapping("/emp")
//public class DemoController2 {
//
//	@Autowired
//	Emp2Service emp2Service;
//
//	@GetMapping(value ={"/","/home"}, produces = "application/json")
//	public String index(Model model) {
//		model.addAttribute("empList", emp2Service.getAll());
//		return "index";
//	}
//
//	@GetMapping(value ="guide", produces = "application/json")
//	public String guide () {
//		return "addemp";
//	}
//
//	@PostMapping(value = "/addemp", produces = "application/json")
//	public String addEmp(String name, String job,String hiredate, Double sal, Double comm) throws ParseException {
//		Emp2 emp =new Emp2();
//		emp.setEname(name);
//		emp.setJob(job);
//		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = sdf.parse(hiredate);
//		emp.setHiredate(date);
//		emp.setSal(sal);
//		emp.setComm(comm);
//		emp2Service.addEmp(emp);
//		return "redirect:/";
//	}
//	@PostMapping(value ="/updateemp", produces = "application/json")
//	public String updateEmp(Integer no, String name, String job,String hiredate, Double sal, Double comm) throws ParseException {
//		Emp2 emp =emp2Service.getEmpById(no);
//		emp.setEname(name);
//		emp.setJob(job);
//		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = sdf.parse(hiredate);
//		emp.setHiredate(date);
//		emp.setSal(sal);
//		emp.setComm(comm);
//		emp2Service.updateEmp(emp);
//		return "redirect:/";
//	}
//
//	@PostMapping(value ="/inquire", produces = "application/json")
//	public String test(Integer no, Model model) {
//		Emp2 emp = emp2Service.getEmpById(no);
//		if (emp == null) {
//			return "redirect:/";
//		}
//		model.addAttribute("emp", emp);
//		return "getemp";
//	}
//
//}
