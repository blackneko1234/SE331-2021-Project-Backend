package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.service.AdminService;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.service.UserService;
import se331.lab.rest.service.VaccineService;
import se331.lab.rest.util.LabMapper;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @GetMapping("/admins")
    ResponseEntity<?> getAdmins() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getAdminDTO(adminService.getAllAdmin()));
    }
}

