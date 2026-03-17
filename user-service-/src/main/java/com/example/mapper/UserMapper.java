package com.example.mapper;

import com.example.dto.UserResponse;
import com.example.entity.User;
import com.example.dto.UserRequest;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        return new User(
                request.getName(),
                request.getEmail(),
                request.getAge()
        );
    }

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAge(user.getAge());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}