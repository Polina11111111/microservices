package com.example.service;

import com.example.dto.UserResponse;

import java.util.List;

import com.example.dto.UserRequest;


public interface UserService {

    UserResponse create(UserRequest request);

    UserResponse getById(Long id);

    List<UserResponse> getAll();

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);
}