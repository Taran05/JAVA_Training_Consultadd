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

    public String updateEmployee(String id, Employee employee){
        Optional<Employee> cur_emp=employeeRepository.findById(id);
        if(cur_emp.isPresent()){
            Employee Temp_Data=cur_emp.get();
            Temp_Data.setName(employee.getName());
            Temp_Data.setAge(employee.getAge());
            employeeRepository.save(Temp_Data);
            return "Employee Data Updated Successfully!";
        }
        else{
            return "ID not found!";
        }
    }

    public String deleteEmployee(String id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return "Employee data deleted successfully!";
        }
        else{
            return "ID doesn't exist";
        }
    }

}
