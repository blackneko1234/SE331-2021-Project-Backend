package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.dao.DoctorDao;
import se331.lab.rest.dao.UserDao;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.security.entity.User;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    DoctorDao doctorDao;
    @Override
    public Integer getUserSize() {
        return userDao.getUserSize();
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userDao.getUsers(pageSize, page);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

//    @Override
//    @Transactional
//    public User save(User user) {
//        Doctor doctor = doctorDao.findById(user.getDoctor().getId()).orElse(null);
//        user.setDoctor(doctor);
//        doctor.getPatientlist().add(user);
//        return userDao.save(user);
//    }

    @Override
    public Page<User> getUsers(String title, Pageable pageable) {
        return userDao.getUser(title,pageable);
    }
}
