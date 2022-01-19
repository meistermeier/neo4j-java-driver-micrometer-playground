package org.neo4j.driver.metricsverification;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Something {

    private final Driver driver;

    @Autowired
    public Something(Driver driver) {
        this.driver = driver;
    }

    @GetMapping("/")
    public void doSth() {
        driver.session().run("MATCH (n) return n").consume();
    }
}
