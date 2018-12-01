package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User,Long> {

    @Override
    <S extends User> S save(S s);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCase(String username);

    User findByUsernameIgnoreCase(String username);

    User findByEmailIgnoreCaseAndPass(String email, String pass);
}
