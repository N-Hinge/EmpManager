package com.example.EmpManager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.EmpManager.Entity.Email;
import com.example.EmpManager.Entity.Employee;
import com.example.EmpManager.Entity.Vendor;
import com.example.EmpManager.Exception.ResourceNotFoundException;
import com.example.EmpManager.Repository.VendorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
    
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
   
    public Optional<Vendor> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }
    

    public Vendor updateVendor(Long id, Vendor vendorDetails) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            vendor.setName(vendorDetails.getName());
            vendor.setEmail(vendorDetails.getEmail());
            vendor.setUpi(vendorDetails.getUpi());
            return vendorRepository.save(vendor);
        } else {
            throw new ResourceNotFoundException("Vendor not found with id " + id);
        }
    }

    public void deleteVendor(Long id) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            vendorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Vendor not found with id " + id);
        }
    }
}
