package com.spring.springredditclone.repository;

import com.spring.springredditclone.model.Post;
import com.spring.springredditclone.model.User;
import com.spring.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}