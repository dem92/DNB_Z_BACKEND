package no.westerdals.pj3100g15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"no.westerdals.pj3100g15"})
public class DnbZBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnbZBackendApplication.class, args);
    }
}
