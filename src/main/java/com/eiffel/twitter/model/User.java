package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @Column(name = "user_id")
    private long id;

    private String name,lastName;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @Transient //To not create the fields in the DB
    private long followersCount, followingCount, notiCount, tweetCount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private Date creationDate;

    //Follow
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Follow> followers;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Follow> followings;

    //Notification
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> notifications;

    //Favourite
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favourite> favourites;

    //Tweets
    //@JsonProperty( access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweets;


    //Retweet
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Retweet> retweets;


    public User() {
        this.creationDate = new Date();
    }

    public long getFollowersCount() {
        return followers.size();
    }

    public long getFollowingCount() {
        return followings.size();
    }

    public long getNotiCount() {
        int cont=0;
        for (Notification n: notifications) {
            if (!n.isReaded()) {
                cont = cont + 1;
            }
        }
        return cont;
    }

    public long getTweetCount() {
        return tweets.size();
    }

    public void setCreationDate() {
        this.creationDate = new Date();
    }
}
