package de.hystrix;

import de.PingClient;
import de.RequestDTO;
import de.ResponseDTO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "PingClientFallback")
public class PingClientFallback implements PingClient {

    private final Throwable cause;

    PingClientFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ResponseDTO ping(RequestDTO requestDTO) throws Exception {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            log.error("Feign client error for service. Status: {}", ((FeignException) cause).status());
            throw new Exception("Hahaha Server unavailable");
        }

        log.error("Client error for service.", cause);
        throw new Exception("Server unavailable. Unknown reason.");
    }
}
