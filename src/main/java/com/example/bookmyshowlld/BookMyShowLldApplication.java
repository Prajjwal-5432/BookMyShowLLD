package com.example.bookmyshowlld;

import com.example.bookmyshowlld.controllers.*;
import com.example.bookmyshowlld.dtos.CreateUserRequestDto;
import com.example.bookmyshowlld.dtos.CreateUserResponseDto;
import com.example.bookmyshowlld.models.City;
import com.example.bookmyshowlld.models.Language;
import com.example.bookmyshowlld.models.SeatType;
import com.example.bookmyshowlld.models.Show;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BookMyShowLldApplication implements CommandLineRunner {
    private UserController userController;
    private CityController cityController;
    private TheatreController theatreController;
    private ShowController showController;
    private TicketController ticketController;
    @Autowired
    public BookMyShowLldApplication(UserController userController,
                                    CityController cityController,
                                    TheatreController theatreController,
                                    ShowController showController,
                                    TicketController ticketController) {
        this.userController = userController;
        this.cityController = cityController;
        this.theatreController = theatreController;
        this.showController = showController;
        this.ticketController = ticketController;
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

        this.theatreController.addAuditorium(1L, "Audi1", 123);

        Map<SeatType, Integer> seatsforAuditorium = new HashMap<>();
        seatsforAuditorium.put(SeatType.VIP, 20);
        seatsforAuditorium.put(SeatType.GOLD, 100);

        this.theatreController.addSeats(1L, seatsforAuditorium);
        this.showController.createShow(1L, new Date(), new Date(),
                            1L, null, Language.ENGLISH);

//        this.ticketController.bookTicket(
//                1L,
//                List.of(20L, 21L, 22L),
//                1L
//                );

        TicketBookRunner ticketBookRunner1 = new TicketBookRunner(
                this.ticketController,
                1L,
                List.of(58L, 59L, 60L),
                1L
        );

        TicketBookRunner ticketBookRunner2 = new TicketBookRunner(
                this.ticketController,
                1L,
                List.of(60L, 61L, 62L),
                1L
        );

        Thread t1 = new Thread(ticketBookRunner1);
        Thread t2 = new Thread(ticketBookRunner2);
        t1.start();
        t2.start();

    }
}
