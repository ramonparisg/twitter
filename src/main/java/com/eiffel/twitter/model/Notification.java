package com.eiffel.twitter.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id")
    private long id;

    private String text;

    private Date date;

    private boolean readed;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private User user;

    public Notification() {
    }
}
