package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Tweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tweet_id")
    private long id;

    private Date date;
    private String text;
    private long favQuantity;
    private long rtQuantity;
    private long repQuantity;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favourite> favourites;



    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Retweet> retweets;



    @OneToMany(mappedBy = "baseTweet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reply> baseTweets;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL)
    private List<Reply> replies;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tweets", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Hashtag> hashtags;

    public Tweet() {
    }

    public long getFavQuantity() {
        return favQuantity;
    }

    public void setFavQuantity() {
        this.favQuantity = favourites.size();
    }

    public long getRtQuantity() {
        return rtQuantity;
    }

    public void setRtQuantity() {
        this.rtQuantity = retweets.size();
    }

    public long getRepQuantity() {
        return repQuantity;
    }

    public void setRepQuantity() {
        this.repQuantity = replies.size();
    }
}
