package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationDao extends JpaRepository<Notification,Long> {

}
