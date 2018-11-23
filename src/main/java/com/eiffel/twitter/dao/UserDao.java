package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long> {

    @Override
    <S extends User> S save(S s);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);
}
