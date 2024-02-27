package com.musalasoft.drone.controllers;

import com.musalasoft.drone.payloads.requests.LoginRequest;
import com.musalasoft.drone.payloads.requests.UserRequestDto;
import com.musalasoft.drone.payloads.responses.LoginResponse;
import com.musalasoft.drone.payloads.responses.UserResponse;
import com.musalasoft.drone.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/users")
public class UserController {

   private final UserService userService;

    @PostMapping("/create")
    public UserResponse createAdmin(@RequestBody @Valid UserRequestDto userRequest)  {
        return userService.createAdmin(userRequest);
    }

    @PostMapping("/loginUser")
    public LoginResponse loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);

    }
}
