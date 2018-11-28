package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "followConstraint", columnNames = {"follower","following"}))

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

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }
}
