package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    private String username,name,lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = true)
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private long followersCount, followingCount, notCount, tweetCount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;

    private Date creationDate;

    private boolean privateUser;

    //Follow
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follow> followers;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followings;

    //Notification
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    //Favourite
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favourite> favourites;

    //Tweets
    @JsonProperty( access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweets;


    //Retweet
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Retweet> retweets;


    public User() {
    }

}
