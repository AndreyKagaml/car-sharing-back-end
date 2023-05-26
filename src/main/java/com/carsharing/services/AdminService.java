package com.carsharing.services;

import com.carsharing.models.Admin;
import com.carsharing.repositories.AdminRepository;
import com.carsharing.validators.AdminValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminValidator adminValidator;

    public AdminService(
        AdminRepository adminRepository,
        AdminValidator adminValidator
    ) {
        this.adminRepository = adminRepository;
        this.adminValidator = adminValidator;
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(long id) {
        adminValidator.validate(id, adminRepository.findAll());
        return adminRepository.findById(id).orElseThrow();
    }

    public void save(Admin admin) {
        adminValidator.validate(admin, adminRepository.findAll());
        adminRepository.save(admin);
    }

    public void update(Admin admin, long id) {
        adminValidator.validate(admin, adminRepository.findAll());
        adminRepository.update(id, admin);
    }

    public void delete(Admin admin) {
        adminValidator.validate(admin, adminRepository.findAll());
        adminRepository.delete(admin);
    }

    public Admin findByEmail(String email) {
        adminValidator.validate(email, adminRepository.findAll());
        return adminRepository.findByEmail(email).orElseThrow();
    }
}