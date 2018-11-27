package com.eiffel.twitter.controller;

import com.eiffel.twitter.dao.UserDao;
import com.eiffel.twitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController

public class UserController {
    @Autowired
    UserDao userDao;

    @GetMapping("/")
    public String hello(){
        return "Hola";
    }

    @GetMapping("/@{username}")
    public ResponseEntity<?> profile(@PathVariable("username") String username){

        if (!userDao.existsByUsername(username))
            return new ResponseEntity(false,HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(userDao.findByUsername(username),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        try {
            userDao.save(user);
        }catch (Exception e){
            return new ResponseEntity(false, HttpStatus.IM_USED);
        }
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User u){
        User logged = userDao.findByEmailAndPass(u.getEmail(),u.getPass());;
        return new ResponseEntity(logged,logged == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

}
