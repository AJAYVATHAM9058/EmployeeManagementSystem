package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);

	void deleteEmployeeById(long id);
	
	Employee fetchEmployeeById(long id);
	
	Page<Employee> findPaginated(int pageNo,int pageSzie,String sortField,String sortDir);
	
}


