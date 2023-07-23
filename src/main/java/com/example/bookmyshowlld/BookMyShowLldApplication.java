package com.example.bookmyshowlld;

import com.example.bookmyshowlld.controllers.CityController;
import com.example.bookmyshowlld.controllers.TheatreController;
import com.example.bookmyshowlld.controllers.UserController;
import com.example.bookmyshowlld.dtos.CreateUserRequestDto;
import com.example.bookmyshowlld.dtos.CreateUserResponseDto;
import com.example.bookmyshowlld.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowLldApplication implements CommandLineRunner {
    private UserController userController;
    private CityController cityController;
    private TheatreController theatreController;
    @Autowired
    public BookMyShowLldApplication(UserController userController,
                                    CityController cityController,
                                    TheatreController theatreController) {
        this.userController = userController;
        this.cityController = cityController;
        this.theatreController = theatreController;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowLldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setEmail("prajjwalsahu9876@gmail.com");

        CreateUserResponseDto responseDto = userController.createUser(requestDto);

        this.cityController.addCity("Chandigarh");
        this.theatreController.createTheatre(
                "PVR",
                "XYZ sector",
                1L
        );
    }
}
