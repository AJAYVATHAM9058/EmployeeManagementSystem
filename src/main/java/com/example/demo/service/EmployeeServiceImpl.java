package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
				Sort.by(sortField).descending();
		
		org.springframework.data.domain.Pageable  pageable =  (org.springframework.data.domain.Pageable)PageRequest.of(pageNo - 1, pageSize,sort);
		
		return this.employeeRepository.findAll(pageable);

	}

}
