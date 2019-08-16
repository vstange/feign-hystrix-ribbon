package de;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "ProofController")
public class PingController {

    @SuppressWarnings("FieldCanBeLocal")
    private boolean throwException = false;

    @RequestMapping(
            value = "/ping",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseDTO ping(
            @RequestBody RequestDTO requestDTO
    ) throws Exception {
        if (throwException) {
            throw new Exception();
        }
        return new ResponseDTO("Hello, " + requestDTO.getText() + ".");
    }
}
