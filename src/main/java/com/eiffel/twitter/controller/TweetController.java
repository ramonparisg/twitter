package com.eiffel.twitter.controller;

import com.eiffel.twitter.dao.TweetDao;
import com.eiffel.twitter.dao.UserDao;
import com.eiffel.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class TweetController {

    @Autowired
    TweetDao tweetDao;

    @Autowired
    UserDao userDao;

    @PostMapping("/tweet")
    public ResponseEntity<?> tweet(@RequestBody Tweet tweet){
        try {
            tweet.setUser(userDao.findByUsername(tweet.getUser().getUsername()));
            Tweet t = tweetDao.save(tweet);
        } catch (Exception e){
            return new ResponseEntity(false,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(true,HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> followingTweets(@RequestParam("username") String username, @RequestParam("page") int page){
        Pageable pageable = PageRequest.of(page,25, Sort.by("date").descending());
        return new ResponseEntity(tweetDao.findAllByUserFollowingsFollowerUsername(username,pageable), HttpStatus.OK);
    }


}
