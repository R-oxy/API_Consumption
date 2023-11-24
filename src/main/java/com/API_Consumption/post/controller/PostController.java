package com.API_Consumption.post.controller;

import com.API_Consumption.post.controller.dto.PostDto;
import com.API_Consumption.post.service.PostService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/posts")
    public Page<PostDto> getPosts(Pageable pageable){
        return postService.getPosts(pageable);
    }

    @GetMapping("/posts/{id}")
    public PostDto getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @GetMapping("/posts/user/{userId}")
    public Page<PostDto> getPostsByUserId(@PathVariable Integer userId, Pageable pageable){
        return postService.getPostsByUserId(userId, pageable);
    }

    @PutMapping("/posts/{id}")
    public PostDto updatePostById(@PathVariable Integer id, @RequestBody PostDto postDto) {
        return postService.updatePostById(id, postDto);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }

    // calculate the average character length of the titles of all posts
    @GetMapping("/posts/averageCharacterLength")
    public double getAverageCharacterLength() {
            return postService.getAverageCharacterLength();
    }


}
