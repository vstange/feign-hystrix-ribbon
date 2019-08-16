package de.hystrix;

import de.PingClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PingClientFallbackFactory implements FallbackFactory<PingClient> {

    @Override
    public PingClient create(Throwable e) {
        return new PingClientFallback(e);
    }
}
