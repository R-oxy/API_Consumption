package com.API_Consumption.post.controller.assembler;

import com.API_Consumption.post.controller.dto.PostDto;
import com.API_Consumption.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostAssembler {

    public static PostDto toPostDto(Post fromPost) {
        PostDto postDto = new PostDto();
        postDto.setId(fromPost.getId());
        postDto.setUserId(fromPost.getUserId());
        postDto.setTitle(fromPost.getTitle());
        postDto.setBody(fromPost.getBody());
        return postDto;
    }

    public static Post toPost(PostDto from, Post to){
        to.setId(from.getId());
        to.setUserId(from.getUserId());
        to.setTitle(from.getTitle());
        to.setBody(from.getBody());
        return to;
    }
}
