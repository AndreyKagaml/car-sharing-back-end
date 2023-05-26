package com.carsharing.controllers;

import com.carsharing.models.Admin;
import com.carsharing.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(
        AdminService adminService
    ) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public List<Admin> findAll() {
        return adminService.findAll();
    }

    @GetMapping("/{id}")
    public Admin findById(@PathVariable Long id) {
        return adminService.findById(id);
    }

    @PostMapping("/")
    public Admin save(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @PutMapping("/{id}")
    public Admin update(@RequestBody Admin admin, @PathVariable Long id) {
        return adminService.update(admin, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        adminService.delete(new Admin(id));
    }
}
