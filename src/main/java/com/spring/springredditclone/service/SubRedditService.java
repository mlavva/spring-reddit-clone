package com.spring.springredditclone.service;

import com.spring.springredditclone.exception.SpringRedditException;
import com.spring.springredditclone.mapper.SubredditMapper;
import com.spring.springredditclone.model.SubReddit;
import com.spring.springredditclone.repository.SubRedditRepository;
import dto.SubRedditDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubRedditService {
    private final SubRedditRepository subRedditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubRedditDTO save(SubRedditDTO subRedditDTO) {
        SubReddit saved = subRedditRepository.save(subredditMapper.mapDtoToSubreddit(subRedditDTO));
        subRedditDTO.setId(saved.getId());
        return subRedditDTO;
    }

    @Transactional(readOnly = true)
    public List<SubRedditDTO> getAll() {
        return subRedditRepository.findAll().stream().map(subredditMapper::mapSubredditToDto).collect(Collectors.toList());
    }

    public SubRedditDTO getSubReddit(Long id) {
        SubReddit subReddit = subRedditRepository.findById(id).orElseThrow(() -> new SpringRedditException("No subreddit found with id: " + id));
        return subredditMapper.mapSubredditToDto(subReddit);
    }
}
