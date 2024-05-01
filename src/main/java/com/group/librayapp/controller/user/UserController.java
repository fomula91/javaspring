package com.group.librayapp.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.group.librayapp.domain.user.User;
import com.group.librayapp.dto.user.request.UserCreateRequest;
import com.group.librayapp.dto.user.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        
        users.add(new User(request.getName(), request.getAge()));
        
    }
    
    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        List<UserResponse> responses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            responses.add(new UserResponse(i+1, users.get(i)));
        }
        return responses;
    }
    
}
