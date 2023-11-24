package com.API_Consumption.user.controller.assembler;

import com.API_Consumption.user.controller.dto.UserDto;
import com.API_Consumption.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public static UserDto toUserDto(User from) {
        UserDto to = new UserDto()
                .setId(from.getId())
                .setName(from.getName())
                .setUsername(from.getUsername())
                .setEmail(from.getEmail())
                .setAddress(from.getAddress())
                .setPhone(from.getPhone())
                .setWebsite(from.getWebsite())
                .setCompany(from.getCompany());

        return to;
    }

    public static User toUser(UserDto from, User to){
        to.setId(from.getId());
        to.setName(from.getName());
        to.setUsername(from.getUsername());
        to.setEmail(from.getEmail());
        to.setAddress(from.getAddress());
        to.setPhone(from.getPhone());
        to.setWebsite(from.getWebsite());
        to.setCompany(from.getCompany());
        return to;
    }
}
