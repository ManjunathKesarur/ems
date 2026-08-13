package com.tcs.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.entity.Employee;
import com.tcs.ems.exception.MissingFieldException;
import com.tcs.ems.exception.UserAlreadyExistsException;
import com.tcs.ems.exception.UserNotFoundException;
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
	
	
	
	public Employee fetchEmployeeByEmail(String email) {
		
		Optional<Employee> oe = employeeRepository.findById(email);
		if(oe.isPresent()) {
			return oe.get();
		}else {
		
			throw new UserNotFoundException("there is no data with email "+email);
		}
	}

	
	
	public List<Employee> fetchAllEmployees(){
		
		List<Employee> employees=employeeRepository.findAll();
	
			if(employees.isEmpty()) {
				throw new UserNotFoundException("No Data's are present to fetch");
			}else {
				return 	employees;
			}
	}

	
	
	public String deleteEmployeeByEmail(String email) {

		Optional<Employee> oe = employeeRepository.findById(email);
		if(oe.isPresent()) {
			employeeRepository.deleteById(email);
			return "enter email data deleted";
		}else {
			throw new UserNotFoundException("enterd email's data is not present in database");
		}
	}

	
	
	public String updatePutEmployeeByEmail(Employee employee) {
		
		Optional<Employee> optionals = employeeRepository.findById(employee.getEmail());
		
		if(optionals.isPresent()) {
			
			if (employee.getName() == null || employee.getName().isBlank()) {
	            throw new MissingFieldException("Name field is required for update");
	        }
	        if (employee.getEmail() == null || employee.getEmail().isBlank()) {
	            throw new MissingFieldException("Email field is required for update");
	        }
	        if (employee.getDepartment() == null || employee.getDepartment().isBlank()) {
	            throw new MissingFieldException("Department field is required for update");
	        }
	        if (employee.getSalary() == null ) { 
	            throw new MissingFieldException("Salary field is required for update");
	        }
	        if (employee.getSalary()<0) {
	        	throw new MissingFieldException("Salary field must be positive");
	        }
			
			employeeRepository.save(employee);
				return "data updated";
			
		}else {
				throw new UserNotFoundException("enterd email not exist so cant update the data");
		}
	}
	
	
	

	
}
