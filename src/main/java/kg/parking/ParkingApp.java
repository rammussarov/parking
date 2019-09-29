package kg.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ParkingApp {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApp.class, args);
    }
}
