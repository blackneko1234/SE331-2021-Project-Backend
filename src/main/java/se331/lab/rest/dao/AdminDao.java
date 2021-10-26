package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Admin;

import java.util.Optional;

public interface AdminDao {
    Page<Admin> getAdmin(Pageable pageRequest);

    Optional<Admin> findById(Long id);
}
