package com.scaler.boookmyshowmay23;

import com.scaler.boookmyshowmay23.controllers.UserController;
import com.scaler.boookmyshowmay23.dtos.SignUpRequestDto;
import com.scaler.boookmyshowmay23.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Boookmyshowmay23Application implements CommandLineRunner {
    @Autowired
    private UserController userController;

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("vivek@scaler.com");
        signUpRequestDto.setPassword("password");
        userController.signUp(signUpRequestDto);
    }

    public static void main(String[] args) {

//        BaseModel bm = new BaseModel();
//        bm.
        SpringApplication.run(Boookmyshowmay23Application.class, args);
//        System.out.println("Hi");


    }

}
