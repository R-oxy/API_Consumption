package com.API_Consumption.user.controller.dto;

import com.API_Consumption.user.entity.Address;
import com.API_Consumption.user.entity.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UserDto {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("username")
	private String username;

	@JsonProperty("email")
	private String email;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("website")
	private String website;

	@JsonProperty("company")
	private Company company;

}
