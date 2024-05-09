package com.group.librayapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.librayapp.dto.user.request.UserCreateRequest;
import com.group.librayapp.dto.user.request.UserUpdateRequest;
import com.group.librayapp.dto.user.response.UserResponse;
import com.group.librayapp.repository.user.UserRepository;

@Service
public class UserService {

private final UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;

}

    public void updateUser( UserUpdateRequest request) {
        
        if(userRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName( request.getId(), request.getName());
    }

    public void deleteUser(String name) {
        if(userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteUser(name);
    }

    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }
    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }
}
