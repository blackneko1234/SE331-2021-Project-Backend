package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.security.entity.User;

import java.util.Optional;

public interface UserDao {
    Integer getUserSize();
    Page<User> getUsers(Integer pageSize, Integer page);
    User getUser(Long id);

    User save(User user);
    Page<User> getUser(String name, Pageable page);

    Page<User> getUser(Pageable pageRequest);
}
