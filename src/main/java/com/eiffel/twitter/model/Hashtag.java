package com.eiffel.twitter.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Hashtag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hashtag_id")
    private long id;

    private String text;

    @ManyToMany
    @JoinTable(name = "Tweets_has_hashtags", joinColumns = {
            @JoinColumn(referencedColumnName = "hashtag_id", name = "hashtag_id")},
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "tweet_id", name = "tweet_id") })
    private List<Tweet> tweets;


    public Hashtag() {
    }

    public Hashtag(String text) {
        this.text = text;
    }
}
