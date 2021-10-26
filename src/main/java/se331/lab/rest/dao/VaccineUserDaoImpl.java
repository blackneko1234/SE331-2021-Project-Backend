package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.entity.VaccineUser;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.repository.VaccineUserRepository;

import java.util.Optional;

@Repository
public class VaccineUserDaoImpl implements VaccineUserDao {

    @Autowired
    VaccineUserRepository vaccineUserRepository;

    @Override
    public VaccineUser getUserVaccine(Long id) {
        return vaccineUserRepository.findById(id).orElse(null);
    }

    @Override
    public VaccineUser save(VaccineUser vaccineUser) {
        return vaccineUserRepository.save(vaccineUser);
    }

}
