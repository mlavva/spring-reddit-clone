package com.spring.springredditclone.service;

import com.spring.springredditclone.exception.PostNotFoundException;
import com.spring.springredditclone.mapper.CommentMapper;
import com.spring.springredditclone.model.Comment;
import com.spring.springredditclone.model.Post;
import com.spring.springredditclone.model.User;
import com.spring.springredditclone.repository.CommentRepository;
import com.spring.springredditclone.repository.PostRepository;
import com.spring.springredditclone.repository.UserRepository;
import dto.CommentsDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentsService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDTO commentsDTO) {
       Post post = postRepository.findById(commentsDTO.getPostId()).orElseThrow(() -> new PostNotFoundException(commentsDTO.getPostId().toString()));
       Comment comment = commentMapper.map(commentsDTO, post, authService.getCurrentUser());
       String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post.");
       sendCommentNotification(message, post.getUser());
       commentRepository.save(comment);
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendEmail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDTO> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post).stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }

    public List<CommentsDTO> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user).stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }
}
