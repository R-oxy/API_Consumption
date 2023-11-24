package com.API_Consumption.user.service;

import com.API_Consumption.user.controller.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> getUsers(Pageable pageable);

    UserDto getUserById(Integer id);
}
