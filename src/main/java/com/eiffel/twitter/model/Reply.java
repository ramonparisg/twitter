package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Reply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private long id;

    private Date date;

    @ManyToOne
    @JoinColumn(referencedColumnName = "tweet_id", name = "baseTweet")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Tweet baseTweet;

    @ManyToOne
    @JoinColumn(referencedColumnName = "tweet_id", name = "reply")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Tweet reply;

    public Reply() {
    }
}
