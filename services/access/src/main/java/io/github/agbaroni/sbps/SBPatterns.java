package io.github.agbaroni.sbps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import reactor.core.publisher.Mono;

@RequestMapping("/")
@RestController
public class SBPatterns {

    private static final Logger logger = LoggerFactory.getLogger(SBPatterns.class);

    private final ObservationRegistry observationRegistry;

    public SBPatterns(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }

    @GetMapping
    public Mono<String> get() {
        logger.info("GET /");

        Observation.createNotStarted("sbpatterns.index", observationRegistry).observe(() -> {
            System.out.println("OK");
        });

        return Mono.empty();
    }
}