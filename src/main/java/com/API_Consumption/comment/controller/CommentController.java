package com.API_Consumption.comment.controller;

import com.API_Consumption.comment.controller.dto.CommentDto;
import com.API_Consumption.comment.service.CommentService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public Page<CommentDto> getComments(Pageable pageable){
        return commentService.getComments(pageable);
    }

    @GetMapping("/comments/{id}")
    public CommentDto getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/comments/post/{postId}")
    public Page<CommentDto> getCommentsByPostId(@PathVariable Integer postId, Pageable pageable){
        return commentService.getCommentsByPostId(postId, pageable);
    }

    // Get the first five comments
    @GetMapping("/comments/firstFive")
    public Page<CommentDto> getFirstFiveComments(Pageable pageable) {
        return commentService.getFirstFiveComments(pageable);
    }


}
