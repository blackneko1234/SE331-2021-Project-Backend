package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.entity.VaccineUser;

import java.util.List;

public interface VaccineUserRepository extends JpaRepository<VaccineUser, Long> {
    List<VaccineUser> findAll();
}
