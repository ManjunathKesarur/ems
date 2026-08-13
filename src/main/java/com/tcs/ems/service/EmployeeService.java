package com.tcs.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.entity.Employee;
import com.tcs.ems.exception.UserAlreadyExistsException;
import com.tcs.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {

	
	
	private final EmployeeRepository employeeRepository;

	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	
	
	
	public String cretaeEmployee(Employee employee) {
		
		Optional<Employee> oe = employeeRepository.findById(employee.getEmail());
		if(oe.isEmpty()) {
		employeeRepository.save(employee);
		
			return "Employee created successfully";
		
		}else {
			throw new UserAlreadyExistsException("employee with this email : "+employee.getEmail()+" already exist please enter the unique email to register or delete the old account");
			}
	}
	
	
	
	public Object fetchEmployeeByEmail(String email) {
		
		Optional<Employee> oe = employeeRepository.findById(email);
		if(oe.isPresent()) {
			return oe.get();
		}else {
			return "the entered email is not present in databae";
		}
	}

	
	
	public List<Employee> fetchAllEmployees(){
		
		return 	employeeRepository.findAll();
	}

	
	
	public String deleteEmployeeByEmail(String email) {

		Optional<Employee> oe = employeeRepository.findById(email);
		if(oe.isPresent()) {
			employeeRepository.deleteById(email);
			return "enter email data deleted";
		}else {
			return "the entered email is not present in databae";
		}
	}

	
	
	public String updateEmployeeByEmail(Employee employee) {
		employeeRepository.save(employee);
		return "data updated";
	}
	
	
	
}
