package com.eiffel.twitter.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Favourite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fav_id")
    private long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tweet_id", referencedColumnName = "tweet_id")
    private Tweet tweet;

    public Favourite() {
    }
}
