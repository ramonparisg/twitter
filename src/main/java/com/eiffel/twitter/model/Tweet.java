package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private Date date;

    private String text;

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @Transient //To not create the fields in the DB
    private long favCount,rtCount,repCount;

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
        this.date = new Date();
    }

    public long getFavCount() {
        return favourites.size();
    }

    public long getRtCount() {
        return retweets.size();
    }

    public long getRepCount() {
        return replies.size();
    }
}
