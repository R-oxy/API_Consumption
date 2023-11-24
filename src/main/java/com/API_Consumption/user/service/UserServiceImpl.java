package com.API_Consumption.user.service;

import com.API_Consumption.user.controller.assembler.UserAssembler;
import com.API_Consumption.user.controller.dto.UserDto;
import com.API_Consumption.user.entity.User;
import com.API_Consumption.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    private static String url = "https://jsonplaceholder.typicode.com/users";

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {

        UserDto[] users = restTemplate.getForObject(url, UserDto[].class);

        for (UserDto userDto : users) {
            User user = new User(userDto.getId(), userDto.getName(), userDto.getUsername(), userDto.getEmail(), userDto.getAddress(), userDto.getPhone(), userDto.getWebsite(), userDto.getCompany());
            userRepository.save(user);
        }

        Page<User> savedUsers = userRepository.findAll(pageable);

        List<UserDto> savedUserDtos = savedUsers.stream()
                .map(UserAssembler::toUserDto)
                .collect(Collectors.toList());

        return new PageImpl<>(savedUserDtos, pageable, savedUsers.getTotalElements());
    }


    @Override
    public UserDto getUserById(Integer id) {
        UserDto user = restTemplate.getForObject(url + "/" + id, UserDto.class);
        return user;
    }


}
