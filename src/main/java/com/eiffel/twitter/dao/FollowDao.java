package com.eiffel.twitter.dao;

import com.eiffel.twitter.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface FollowDao extends JpaRepository<Follow,Long> {

    Long deleteByFollowerUsernameIgnoreCaseAndFollowingUsernameIgnoreCase(String followerUsername, String followingUsername);
}
