package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.annotation.PostConstruct;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

//	Display list of employees
	@GetMapping("/home")
	public String viewHomePage(Model model) {
		
		return findPaginated(1, model);
		
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {

//		Create a model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		return "new_Employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {

//		Save Employee to Database
		employeeService.saveEmployee(employee);

		return "redirect:/home";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id) {

		employeeService.deleteEmployeeById(id);

		return "redirect:/home";
	}

	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable long id,Model model) {
		
		Employee employee = employeeService.fetchEmployeeById(id);
		
		model.addAttribute("employee", employee);
		
		return "updateEmployeeForm";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute("employee") Employee employee) {

		System.out.println(employee.getEmail());
		
//		Save Employee to Database
		employeeService.saveEmployee(employee);

		return "redirect:/home";
	}

	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable int pageNo,Model model) {
		
		int pageSize = 7;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
		List<Employee> listOfEmployees = page.getContent();
		
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("listOfEmployees",listOfEmployees);
		
		return "index";
		
	}
	
	

}
