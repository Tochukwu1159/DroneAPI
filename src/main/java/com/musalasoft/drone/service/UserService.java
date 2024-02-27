package com.musalasoft.drone.service;


import com.musalasoft.drone.payloads.requests.LoginRequest;
import com.musalasoft.drone.payloads.requests.UserRequestDto;
import com.musalasoft.drone.payloads.responses.LoginResponse;
import com.musalasoft.drone.payloads.responses.UserResponse;

public interface UserService
{
    UserResponse createAdmin(UserRequestDto userRequest);

    LoginResponse loginUser(LoginRequest loginRequest);
}
