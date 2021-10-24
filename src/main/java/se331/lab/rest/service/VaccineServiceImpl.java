package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.Vaccine;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {
    @Autowired
    VaccineDao vaccineDao;
    @Override
    public List<Vaccine> getAllVaccine() { return vaccineDao.getVaccine(Pageable.unpaged()).getContent();
    }
    @Override
    public Page<Vaccine> getVaccine(Integer page, Integer pageSize) {
        return vaccineDao.getVaccine(PageRequest.of(page,pageSize));
    }
}
