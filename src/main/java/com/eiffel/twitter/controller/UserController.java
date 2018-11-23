package com.eiffel.twitter.controller;

import com.eiffel.twitter.dao.UserDao;
import com.eiffel.twitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{username}")
    public ResponseEntity<?> profile(@PathVariable("username") String username){

        if (userDao.existsByUsername(username))
            return new ResponseEntity<Boolean>(false,HttpStatus.IM_USED);

        return new ResponseEntity<User>(userDao.findByUsername(username),HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<?> register(){



        return new ResponseEntity (userDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){

        userDao.save(user);

        return new ResponseEntity<Boolean>(userDao.existsByEmail("Ramon"), HttpStatus.OK);
    }
}
