package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.Retweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetweetDao extends JpaRepository<Retweet,Long> {


    Page<Retweet> findAllByUserFollowingsFollowerUsernameIgnoreCase(String username, Pageable pageable);
}
