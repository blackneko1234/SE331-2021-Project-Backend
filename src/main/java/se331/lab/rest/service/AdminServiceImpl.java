package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.AdminDao;
import se331.lab.rest.entity.Admin;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.getAdmin(Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Admin> getAdmin(Integer page, Integer pageSize) {
        return adminDao.getAdmin(PageRequest.of(page,pageSize));
    }
}
