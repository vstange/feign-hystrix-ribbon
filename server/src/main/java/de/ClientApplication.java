package de;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@Slf4j(topic = "client")
public class ClientApplication implements CommandLineRunner {

    private final PingClient pingClient;

    @Autowired
    public ClientApplication(PingClient pingClient) {
        this.pingClient = pingClient;
    }

    @Override
    public void run(String... args){
        ResponseDTO responseDTO = null;
        try {
            responseDTO = pingClient.ping(new RequestDTO("Vincent"));
            log.info(responseDTO.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
