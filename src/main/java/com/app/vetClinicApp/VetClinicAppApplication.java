package com.app.vetClinicApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// Указывает на то, что данный класс является точкой входа в Spring-приложение
@SpringBootApplication
// Включает автоматическую конфигурацию, исключая конфигурацию источника данных
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
// Указывает на то, что необходимо сканировать пакеты для поиска аннотированных классов Spring
@ComponentScan(basePackages = "com.app.vetClinicApp")
public class VetClinicAppApplication {

    public static void main(String[] args) {
        // Запускает Spring-приложение
        SpringApplication.run(VetClinicAppApplication.class, args);
    }

}
