package com.eiffel.twitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy ")
    private Date date;

    private boolean readed;

    @ManyToOne
    @JoinColumn(name="notificated", referencedColumnName = "user_id")
    @JsonIgnore
    private User notificated;

    @ManyToOne
    @JoinColumn(name="notificator", referencedColumnName = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User notificator;


    public Notification() {
        this.readed = false;
        this.date = new Date();
    }

    public Notification(String text,User notificator, User notificated) {
        this.date = new Date();
        this.readed = false;
        this.text = text;
        this.notificator = notificator;
        this.notificated = notificated;
    }
}
