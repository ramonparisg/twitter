package com.eiffel.twitter.controller;

import com.eiffel.twitter.dao.FollowDao;
import com.eiffel.twitter.dao.NotificationDao;
import com.eiffel.twitter.dao.UserDao;
import com.eiffel.twitter.model.Follow;
import com.eiffel.twitter.model.Notification;
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

    @Autowired
    FollowDao followDao;

    @Autowired
    NotificationDao notificationDao;

    @GetMapping("/")
    public String hello(){
        return "Hola";
    }

    @GetMapping("/@{username}")
    public ResponseEntity<?> profile(@PathVariable("username") String username){
        User u = userDao.findByUsernameIgnoreCase(username);
        return new ResponseEntity<User>(u, u == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
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
        User logged = userDao.findByEmailIgnoreCaseAndPass(u.getEmail(),u.getPass());;
        return new ResponseEntity(logged,logged == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/follow")
    public ResponseEntity<?> follow(@RequestParam("follower") String followerUsername, @RequestParam("following") String followingUsername){
        User follower = userDao.findByUsernameIgnoreCase(followerUsername);
        User following = userDao.findByUsernameIgnoreCase(followingUsername);
        if (follower == null || following == null)
            return new ResponseEntity(false,HttpStatus.NO_CONTENT);

        Follow f  =  new Follow(follower,following);
        Notification n = new Notification("@"+follower.getUsername() + " is following you",follower,following);
        try {
            followDao.save(f);
            notificationDao.save(n);
        }catch (Exception e){
            return new ResponseEntity(false,HttpStatus.IM_USED);
        }

        return new ResponseEntity(true,HttpStatus.OK);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<?> unfollow(@RequestParam("follower") String followerUsername, @RequestParam("following") String followingUsername){
        try {
            if (followDao.deleteByFollowerUsernameIgnoreCaseAndFollowingUsernameIgnoreCase(followerUsername,followingUsername) == 0)
                    return new ResponseEntity(false,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(false,HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity(true,HttpStatus.OK);
    }

}
