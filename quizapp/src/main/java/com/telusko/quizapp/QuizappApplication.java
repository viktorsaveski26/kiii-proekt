package com.telusko.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
public class QuizappApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizappApplication.class, args);
    }
    //nie imame cel da napravime kviz... veke imame kontroler za prasanja.
    //imame admin i korisnici
    //as an admin i can create a quiz
    // as a user you should just see the questions and the given answers on the screen to choose one

}


