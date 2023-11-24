package com.API_Consumption.post.service;

import com.API_Consumption.post.controller.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<PostDto> getPosts(Pageable pageable);

    PostDto getPostById(Integer id);

    Page<PostDto> getPostsByUserId(Integer userId, Pageable pageable);

    PostDto updatePostById(Integer id, PostDto postDto);

    void deletePost(Integer id);

    double getAverageCharacterLength();

    PostDto createPost(PostDto postDto);
}
