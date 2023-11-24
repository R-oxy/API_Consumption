package com.API_Consumption.post.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class PostDto{

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("userId")
	private Integer userId;

	@JsonProperty("title")
	private String title;

	@JsonProperty("body")
	private String body;

}