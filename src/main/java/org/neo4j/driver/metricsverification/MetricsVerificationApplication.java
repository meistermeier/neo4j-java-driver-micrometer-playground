package org.neo4j.driver.metricsverification;

import io.micrometer.core.instrument.MeterRegistry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.internal.metrics.MicrometerMetricsProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MetricsVerificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsVerificationApplication.class, args);
    }

    @Bean
    public Driver driver(MeterRegistry metricsRegistry) {
        var micrometerMetricsProvider = new MicrometerMetricsProvider(metricsRegistry);
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "secret"),
                Config.builder().withDriverMetrics(micrometerMetricsProvider).build());
    }
}
