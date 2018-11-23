package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Follow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "follow_id")
    private long id;



    @ManyToOne
    @JoinColumn(name = "follower", referencedColumnName = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following", referencedColumnName = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User following;

    public Follow() {
    }
}
