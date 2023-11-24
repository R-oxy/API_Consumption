package com.API_Consumption.post.service;

import com.API_Consumption.post.controller.assembler.PostAssembler;
import com.API_Consumption.post.controller.dto.PostDto;
import com.API_Consumption.post.entity.Post;
import com.API_Consumption.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.API_Consumption.post.controller.assembler.PostAssembler.toPost;
import static com.API_Consumption.post.controller.assembler.PostAssembler.toPostDto;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final RestTemplate restTemplate;

    private final PostRepository postRepository;

    private static String url = "https://jsonplaceholder.typicode.com/posts";

    @Override
    public Page<PostDto> getPosts(Pageable pageable) {

        PostDto[] posts = restTemplate.getForObject(url, PostDto[].class);

        for (PostDto postDto : posts) {
            Post post = new Post(postDto.getId(), postDto.getUserId(), postDto.getTitle(), postDto.getBody());
            postRepository.save(post);
        }

        Page<Post> savedPosts = postRepository.findAll(pageable);

        List<PostDto> savedPostDtos = savedPosts.stream()
                .map(PostAssembler::toPostDto)
                .collect(Collectors.toList());

        return new PageImpl<>(savedPostDtos, pageable, savedPosts.getTotalElements());
    }

    @Override
    public PostDto getPostById(Integer id) {
        PostDto post = restTemplate.getForObject(url + "/" + id, PostDto.class);
        return post;
    }

    @Override
    public Page<PostDto> getPostsByUserId(Integer userId, Pageable pageable) {
        PostDto[] posts = restTemplate.getForObject(url + "?userId=" + userId, PostDto[].class);
        Page<PostDto> page = new PageImpl<>(Arrays.asList(posts), pageable, posts.length);
        return page;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        PostDto post = restTemplate.postForObject(url, postDto, PostDto.class);
        PostDto savedPost = toPostDto(postRepository.save(toPost(post, new Post())));
        return savedPost;
    }

    @Override
    public PostDto updatePostById(Integer id, PostDto postDto) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(postDto.getTitle());
            existingPost.setBody(postDto.getBody());
            Post updatedPost = postRepository.save(existingPost);
            return toPostDto(updatedPost);
        } else {
            throw new RuntimeException("Post with ID " + id + " not found");
        }
    }

    @Override
    public void deletePost(Integer id) {

        restTemplate.delete(url + "/" + id);

        postRepository.deleteById(id);
    }

    @Override
    public double getAverageCharacterLength() {
        PostDto[] posts = restTemplate.getForObject(url, PostDto[].class);
        double averageCharacterLength = Arrays.stream(posts)
                .collect(java.util.stream.Collectors.averagingInt(post -> post.getTitle().length()));
        return averageCharacterLength;
    }

}
