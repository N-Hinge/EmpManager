package com.example.EmpManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmpManager.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
