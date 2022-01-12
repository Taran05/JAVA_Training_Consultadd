package com.consultadd.service;

import com.consultadd.model.Employee;
import com.consultadd.repository.EmployeeRepository;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void testGetEmployee(){
        Employee emp1 = new Employee("1", "Ankush", 24, "Agra");
        Employee emp2 = new Employee("2","Amar",27,"New Delhi");
        List<Employee> expectedList = new ArrayList<>();
        expectedList.add(emp1);
        expectedList.add(emp2);
        Mockito.when(employeeRepository.findAll()).thenReturn(expectedList);
        List<Employee> actualList = employeeService.getEmployees();
        Assert.assertEquals(expectedList, actualList);
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testSaveEmployee(){
        Employee emp = new Employee("1", "Suresh", 45, "Indore");
        Mockito.when(employeeRepository.existsById(emp.getId())).thenReturn(false);
        employeeService.saveEmployee(emp);
        Mockito.verify(employeeRepository, Mockito.times(1)).existsById(emp.getId());
        Mockito.verify(employeeRepository, Mockito.times(1)).save(emp);
    }

    @Test
    public void testUpdateEmployee(){
        Employee emp =new Employee("1", "Arun", 34, "Mumbai");
        Mockito.when(employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        employeeService.updateEmployee(emp, emp.getId());
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(emp.getId());
        Mockito.verify(employeeRepository, Mockito.times(1)).save(emp);
    }

    @Test
    public void testDeleteEmployee(){
        Employee emp = new Employee("1", "Bhavesh", 43, "Pune");
        Mockito.when(employeeRepository.existsById(emp.getId())).thenReturn(true);
        employeeService.delEmployee(emp.getId());
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(emp.getId());
        Mockito.verify(employeeRepository, Mockito.times(1)).existsById(emp.getId());
    }

}
