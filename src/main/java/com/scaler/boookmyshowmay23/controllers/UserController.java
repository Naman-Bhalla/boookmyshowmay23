package com.scaler.boookmyshowmay23.controllers;

import com.scaler.boookmyshowmay23.dtos.ResponseStatus;
import com.scaler.boookmyshowmay23.dtos.SignUpRequestDto;
import com.scaler.boookmyshowmay23.dtos.SignUpResponseDto;
import com.scaler.boookmyshowmay23.models.User;
import com.scaler.boookmyshowmay23.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto request) {
        User user;
        SignUpResponseDto response = new SignUpResponseDto();

        try {
            user = userService.signUp(request.getEmail(), request.getPassword());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setUserId(user.getId());
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}
