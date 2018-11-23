package com.eiffel.twitter.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public Retweet() {
    }

    public Retweet(Date date) {
        this.date = date;
    }
}
