package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Vaccine;

import java.util.List;

public interface VaccineService {
    List<Vaccine> getAllVaccine();
    Page<Vaccine> getVaccine(Integer page, Integer pageSize);
}
