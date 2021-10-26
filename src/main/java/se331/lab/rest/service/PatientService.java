package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.VaccineUser;

public interface PatientService {
    Integer getPatientSize();

    Page<Patient> getPatients(Integer pageSize, Integer page);

    Patient getPatient(Long id);

    VaccineUser save(VaccineUser vaccineUser);

    Page<Patient> getPatients(String title, Pageable pageable);
}
