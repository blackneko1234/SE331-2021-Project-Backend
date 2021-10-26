package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.VaccineUser;

public interface VaccineUserDao {

    VaccineUser getUserVaccine(Long id);

    VaccineUser save(VaccineUser userVaccine);
    
}
