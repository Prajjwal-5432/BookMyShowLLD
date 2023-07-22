package com.example.bookmyshowlld;

import com.example.bookmyshowlld.controllers.UserController;
import com.example.bookmyshowlld.dtos.CreateUserRequestDto;
import com.example.bookmyshowlld.dtos.CreateUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowLldApplication implements CommandLineRunner {
    private UserController userController;
    @Autowired
    public BookMyShowLldApplication(UserController userController) {
        this.userController = userController;
    }
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowLldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setEmail("prajjwalsahu9876@gmail.com");

        CreateUserResponseDto responseDto = userController.createUser(requestDto);

    }
}
