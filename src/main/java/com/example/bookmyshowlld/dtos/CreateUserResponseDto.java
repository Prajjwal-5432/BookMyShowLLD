package com.example.bookmyshowlld.dtos;

import com.example.bookmyshowlld.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseDto {
    private User user;
}
