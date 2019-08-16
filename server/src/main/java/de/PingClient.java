package de;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hystrix.PingClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        value = "ping",
        url = "https://hyplag-backend.test.gipp.com",
        fallbackFactory = PingClientFallbackFactory.class
)
public interface PingClient {

    @RequestMapping(
            value = "/ping",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @CrossOrigin(origins = "*")
    @HystrixCommand
    public ResponseDTO ping(@RequestBody RequestDTO requestDTO) throws Exception;
}
