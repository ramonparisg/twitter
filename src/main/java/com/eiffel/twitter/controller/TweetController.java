package com.eiffel.twitter.controller;

import com.eiffel.twitter.dao.NotificationDao;
import com.eiffel.twitter.dao.TweetDao;
import com.eiffel.twitter.dao.UserDao;
import com.eiffel.twitter.model.Notification;
import com.eiffel.twitter.model.Tweet;
import com.eiffel.twitter.model.User;
import com.eiffel.twitter.util.TweetParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TweetController {

    @Autowired
    TweetDao tweetDao;

    @Autowired
    UserDao userDao;

    @Autowired
    NotificationDao notificationDao;

    @PostMapping("/tweet")
    public ResponseEntity<?> tweet(@RequestBody Tweet tweet){
        TweetParser tweetParser = new TweetParser(tweet.getText());
        Notification n;
        List<Notification> notifications = new ArrayList<>();

        if (tweet.getText().length() > 140)
            return new ResponseEntity(false,HttpStatus.BAD_REQUEST);

        try {
            tweet.setUser(userDao.findByUsernameIgnoreCase(tweet.getUser().getUsername()));
            Tweet t = tweetDao.save(tweet);

            for (String mention : tweetParser.mentions) {
                User mentioned = userDao.findByUsernameIgnoreCase(mention.replace("@",""));
                if (mentioned != null) {
                    n = new Notification("@" + tweet.getUser().getUsername() + " has mentioned you",tweet.getUser(),mentioned);
                    notifications.add(n);
                }
            }

            if (notifications.size() > 0)
                notificationDao.saveAll(notifications);

        } catch (Exception e){
            return new ResponseEntity(false,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(notifications,HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> followingTweets(@RequestParam("username") String username, @RequestParam("page") int page){
        Pageable pageable = PageRequest.of(page,25, Sort.by("date").descending());
        return new ResponseEntity(tweetDao.findAllByUserFollowingsFollowerUsernameIgnoreCase(username,pageable), HttpStatus.OK);
    }


}
