package com.example.bookmyshowlld.controllers;

import com.example.bookmyshowlld.dtos.CreateUserRequestDto;
import com.example.bookmyshowlld.dtos.CreateUserResponseDto;
import com.example.bookmyshowlld.models.User;
import com.example.bookmyshowlld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        User savedUser = userService.createUser(requestDto.getEmail());
        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setUser(savedUser);

        return responseDto;
    }
}
