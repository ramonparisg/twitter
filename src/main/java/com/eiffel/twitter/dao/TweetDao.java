package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetDao extends JpaRepository<Tweet,Long> {

    Page<Tweet> findAllByUserFollowingsFollowerUsername(String username,Pageable pageable);

}
