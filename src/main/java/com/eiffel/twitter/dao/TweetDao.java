package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.Retweet;
import com.eiffel.twitter.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TweetDao extends JpaRepository<Tweet,Long> {


    //Encontrar todos los tweets hecho por el usuario que entre sus seguidores esté yo (el del dashboard)
    Page<Tweet> findAllByUserFollowingsFollowerUsernameIgnoreCase(String username,Pageable pageable);


    //OrRetweetsUserFollowingsFollowerUsername

}
