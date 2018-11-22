package com.eiffel.twitter.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Retweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "retweet_id")
    private long id;

    private Date date;

    @ManyToOne
    @JoinColumn(referencedColumnName = "tweet_id")
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private User user;

    public Retweet() {
    }

    public Retweet(Date date) {
        this.date = date;
    }
}
