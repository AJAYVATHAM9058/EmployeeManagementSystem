package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public Employee fetchEmployeeById(long id) {
		
		return employeeRepository.findById(id).get();
		
	}



}
