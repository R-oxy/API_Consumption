package com.API_Consumption.comment.service;

import com.API_Consumption.comment.controller.assembler.CommentAssembler;
import com.API_Consumption.comment.controller.dto.CommentDto;
import com.API_Consumption.comment.entity.Comment;
import com.API_Consumption.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    private final RestTemplate restTemplate;

    private static String url = "https://jsonplaceholder.typicode.com/comments";

    @Override
    public Page<CommentDto> getComments(Pageable pageable) {

        CommentDto[] comments = restTemplate.getForObject(url, CommentDto[].class);

        for (CommentDto commentDto : comments) {
            Comment comment = new Comment(commentDto.getId(), commentDto.getName(), commentDto.getPostId(), commentDto.getEmail(), commentDto.getBody());
            commentRepository.save(comment);
        }

        Page<Comment> savedComments = commentRepository.findAll(pageable);

        List<CommentDto> savedCommentDtos = savedComments.stream()
                .map(CommentAssembler::toCommentDto)
                .collect(Collectors.toList());

        return new PageImpl<>(savedCommentDtos, pageable, savedComments.getTotalElements());
    }

    @Override
    public CommentDto getCommentById(Integer id) {
        CommentDto comment = restTemplate.getForObject(url + "/" + id, CommentDto.class);
        return comment;
    }

    @Override
    public Page<CommentDto> getCommentsByPostId(Integer postId, Pageable pageable) {
        CommentDto[] comments = restTemplate.getForObject(url + "?postId=" + postId, CommentDto[].class);
        Page<CommentDto> page = new PageImpl<>(Arrays.asList(comments), pageable, comments.length);
        return page;
    }

    @Override
    public Page<CommentDto> getFirstFiveComments(Pageable pageable) {
        // Set the desired page size (5 comments per page)
        Pageable customPageable = PageRequest.of(0, 5);

        CommentDto[] comments = restTemplate.getForObject(url, CommentDto[].class);

        // Calculate the start and end indices based on the custom pageable
        int start = (int) customPageable.getOffset();
        int end = Math.min((start + customPageable.getPageSize()), comments.length);

        // Get only the comments for the current page
        List<CommentDto> commentsForPage = Arrays.asList(Arrays.copyOfRange(comments, start, end));

        return new PageImpl<>(commentsForPage, customPageable, comments.length);
    }
}
