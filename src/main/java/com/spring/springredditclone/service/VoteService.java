package com.spring.springredditclone.service;

import com.spring.springredditclone.exception.PostNotFoundException;
import com.spring.springredditclone.exception.SpringRedditException;
import com.spring.springredditclone.model.Post;
import com.spring.springredditclone.model.Vote;
import com.spring.springredditclone.model.VoteType;
import com.spring.springredditclone.repository.PostRepository;
import com.spring.springredditclone.repository.VoteRepository;
import dto.VoteDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDTO voteDTO) {
        Post post = postRepository.findById(voteDTO.getPostId()).orElseThrow(() ->
                new PostNotFoundException("Post Not Found with ID - " + voteDTO.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDTO.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDTO.getVoteType() + "'d for this post.");
        }

        if (VoteType.UPVOTE.equals(voteDTO.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDTO, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDTO voteDTO, Post post) {
        return Vote.builder().voteType(voteDTO.getVoteType()).post(post).user(authService.getCurrentUser()).build();
    }
}
