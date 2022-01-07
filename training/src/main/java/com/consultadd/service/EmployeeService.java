package com.consultadd.service;

import com.consultadd.model.Employee;
import com.consultadd.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public String saveEmployee(Employee employee){
        if(employeeRepository.existsById(employee.getId())){
            return "Couldn't save the data. ID already exists!";
        }
        else{
            employeeRepository.save(employee);
            return "Employee data saved successfully!";
        }
    }

}
