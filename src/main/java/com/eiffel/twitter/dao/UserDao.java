package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User,Long> {

    @Override
    <S extends User> S save(S s);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByEmailAndPass(String email, String pass);
}
