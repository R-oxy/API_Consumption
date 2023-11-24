package com.API_Consumption.comment.service;

import com.API_Consumption.comment.controller.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentDto> getComments(Pageable pageable);

    CommentDto getCommentById(Integer id);

    Page<CommentDto> getCommentsByPostId(Integer postId, Pageable pageable);

    Page<CommentDto> getFirstFiveComments(Pageable pageable);
}
