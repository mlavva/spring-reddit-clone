package com.spring.springredditclone.mapper;

import com.spring.springredditclone.model.SubReddit;
import dto.SubRedditDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-13T23:29:59-0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (N/A)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubRedditDTO mapSubredditToDto(SubReddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubRedditDTO subRedditDTO = new SubRedditDTO();

        subRedditDTO.setId( subreddit.getId() );
        subRedditDTO.setName( subreddit.getName() );
        subRedditDTO.setDescription( subreddit.getDescription() );

        subRedditDTO.setNumberOfPosts( mapPosts(subreddit.getPosts()) );

        return subRedditDTO;
    }

    @Override
    public SubReddit mapDtoToSubreddit(SubRedditDTO subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubReddit subReddit = new SubReddit();

        subReddit.setId( subreddit.getId() );
        subReddit.setName( subreddit.getName() );
        subReddit.setDescription( subreddit.getDescription() );

        return subReddit;
    }
}
