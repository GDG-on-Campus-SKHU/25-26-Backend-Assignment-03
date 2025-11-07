package com.gdg.jpapost.service;

import com.gdg.jpapost.domain.Post;
import com.gdg.jpapost.domain.User;
import com.gdg.jpapost.dto.PostInfoResponseDto;
import com.gdg.jpapost.dto.PostSaveRequestDto;
import com.gdg.jpapost.repository.PostRepository;
import com.gdg.jpapost.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor

public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostInfoResponseDto savePost(PostSaveRequestDto postSaveRequestDto) {
        User user = userRepository.findById(postSaveRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Post post = Post.builder()
                .user(user)
                .title(postSaveRequestDto.getTitle())
                .content(postSaveRequestDto.getContent())
                .build();

        postRepository.save(post);

        return PostInfoResponseDto.from(post);
    }

    @Transactional(readOnly = true)
    public PostInfoResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 게시물을 찾을 수 없습니다."));

        return PostInfoResponseDto.from(post);
    }

    @Transactional
    public PostInfoResponseDto updatePost(Long postId, PostSaveRequestDto postSaveRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 게시물을 찾을 수 없습니다."));

        User user = userRepository.findById(postSaveRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        post.update(postSaveRequestDto.getTitle(), postSaveRequestDto.getContent(), user);

        return PostInfoResponseDto.from(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public List<PostInfoResponseDto> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(PostInfoResponseDto::from)
                .toList();
    }

}
