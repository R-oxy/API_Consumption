package com.API_Consumption.comment.controller.assembler;

import com.API_Consumption.comment.controller.dto.CommentDto;
import com.API_Consumption.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentAssembler {

    public static CommentDto toCommentDto(Comment from) {
        CommentDto to = new CommentDto()
                .setPostId(from.getPostId())
                .setId(from.getId())
                .setName(from.getName())
                .setEmail(from.getEmail())
                .setBody(from.getBody());

        return to;
    }

    public static Comment toComment(CommentDto from, Comment to){
        to.setPostId(from.getPostId());
        to.setId(from.getId());
        to.setName(from.getName());
        to.setEmail(from.getEmail());
        to.setBody(from.getBody());
        return to;
    }
}
