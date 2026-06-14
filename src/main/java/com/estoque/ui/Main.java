package com.estoque.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.estoque")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
