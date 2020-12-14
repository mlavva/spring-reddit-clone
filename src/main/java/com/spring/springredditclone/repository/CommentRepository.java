package com.spring.springredditclone.repository;

import com.spring.springredditclone.model.Comment;
import com.spring.springredditclone.model.Post;
import com.spring.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}