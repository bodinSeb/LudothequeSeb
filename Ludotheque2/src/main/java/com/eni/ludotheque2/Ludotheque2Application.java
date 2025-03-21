package com.eni.ludotheque2;

import com.eni.ludotheque2.mock.MockBD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ludotheque2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ludotheque2Application.class, args);
        System.out.println("Bonjour, bienvenue en Java!");
    }
}
