package com.gdg.jpapost.controller;


import com.gdg.jpapost.dto.PostInfoResponseDto;
import com.gdg.jpapost.dto.PostSaveRequestDto;
import com.gdg.jpapost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")

public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostInfoResponseDto> savePost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(postSaveRequestDto));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoResponseDto> getPostById(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostInfoResponseDto> updatePost(@PathVariable Long postId,
                                         @RequestBody PostSaveRequestDto postSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(postId, postSaveRequestDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostInfoResponseDto> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<PostInfoResponseDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPost());
    }
}
