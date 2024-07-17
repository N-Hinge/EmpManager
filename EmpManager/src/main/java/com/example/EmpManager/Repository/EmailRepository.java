package com.example.EmpManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmpManager.Entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
