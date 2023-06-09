package com.carsharing;

import com.carsharing.configs.DatabaseConfig;
import com.carsharing.utility.ImageUtility;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Objects;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
        setDatabase();
    }

    private static void setDatabase() {
        final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                DatabaseConfig.class
        );

        Flyway flyway = (Flyway) context.getBean("flyway");
        flyway.migrate();

        List<String> imageNames = List.of(
                Objects.requireNonNull(ApplicationStarter.class.getClassLoader().getResource(
                        "static/images/chevrolet-camaro.jpg"
                )).getFile()
        );

        ImageUtility utility = new ImageUtility();
        try {
            utility.setImagesToDatabase(imageNames);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
