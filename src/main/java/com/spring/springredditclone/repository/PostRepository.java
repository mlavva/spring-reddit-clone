package com.spring.springredditclone.repository;

import com.spring.springredditclone.model.Post;
import com.spring.springredditclone.model.SubReddit;
import com.spring.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(SubReddit subreddit);

    List<Post> findByUser(User user);
}