package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.repository.AdminRepository;
import se331.lab.rest.repository.DoctorRepository;

import java.util.Optional;

@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Page<Admin> getAdmin(Pageable pageRequest) {
        return adminRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }
}
