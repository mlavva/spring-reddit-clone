package com.spring.springredditclone.controller;

import com.spring.springredditclone.service.SubRedditService;
import dto.SubRedditDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubRedditController {
    private final SubRedditService subRedditService;

    @PostMapping
    public ResponseEntity<SubRedditDTO> createSubReddit(@RequestBody SubRedditDTO subRedditDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subRedditService.save(subRedditDTO));
    }

    @GetMapping
    public ResponseEntity<List<SubRedditDTO>> getAllSubreddits() {
        return ResponseEntity.status(HttpStatus.OK).body(subRedditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubRedditDTO> getSubReddit(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subRedditService.getSubReddit(id));
    }
}
