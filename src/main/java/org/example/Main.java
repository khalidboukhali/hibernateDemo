package org.example;

import org.example.dao.EmployeeDao;
import org.example.entities.Departement;
import org.example.entities.Employee;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test get employee by Departement Name
        List<Employee> employeesInDepartement = EmployeeDao.getEmployeeByDepartementName("IT");
        for (Employee employee: employeesInDepartement) {
            System.out.println("Employees in IT department: " + employee.getNom()+" "+employee.getPrenom());
        }

        // Test get departement by employee id
        Departement employeeDepartement = EmployeeDao.getDepartementByEmployeeId(1L);
        System.out.println("Departement of Employee with ID 1: " + employeeDepartement);

        // Test add employee to departement
        EmployeeDao.addEmployeeToDepartement(2L, 1L);

        // Test delete employee by id
        EmployeeDao.deleteEmployeeById(3L);

        // Test delete departement by id
        EmployeeDao.deleteDepartementById(2L);
    }
}