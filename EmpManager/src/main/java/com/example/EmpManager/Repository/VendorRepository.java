package com.example.EmpManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmpManager.Entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
