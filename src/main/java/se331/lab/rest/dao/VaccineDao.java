package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Vaccine;

import java.util.Optional;

public interface VaccineDao {
    Page<Vaccine> getVaccine(Pageable pageRequest);
    Optional<Vaccine> findById(Long id);
}
