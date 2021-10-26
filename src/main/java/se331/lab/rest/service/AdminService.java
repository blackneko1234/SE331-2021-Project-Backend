package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.entity.Doctor;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmin();
    Page<Admin> getAdmin(Integer page, Integer pageSize);
}
