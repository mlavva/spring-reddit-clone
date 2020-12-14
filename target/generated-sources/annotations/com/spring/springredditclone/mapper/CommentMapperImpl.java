package com.spring.springredditclone.mapper;

import com.spring.springredditclone.model.Comment;
import com.spring.springredditclone.model.Post;
import com.spring.springredditclone.model.User;
import dto.CommentsDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-13T23:29:59-0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (N/A)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentsDTO commentsDto, Post post, User user) {
        if ( commentsDto == null && post == null && user == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( commentsDto != null ) {
            comment.setText( commentsDto.getText() );
        }
        if ( post != null ) {
            comment.setPost( post );
            comment.setUser( post.getUser() );
        }
        comment.setCreatedDate( java.time.Instant.now() );

        return comment;
    }

    @Override
    public CommentsDTO mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentsDTO commentsDTO = new CommentsDTO();

        commentsDTO.setId( comment.getId() );
        commentsDTO.setCreatedDate( comment.getCreatedDate() );
        commentsDTO.setText( comment.getText() );

        commentsDTO.setUserName( comment.getUser().getUsername() );
        commentsDTO.setPostId( comment.getPost().getPostId() );

        return commentsDTO;
    }
}
