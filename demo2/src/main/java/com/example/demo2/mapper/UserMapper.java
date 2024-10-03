package com.example.demo2.mapper;

import com.example.demo2.entity.user.User;
import com.example.demo2.model.request.authentication.RegisterRequest;
import com.example.demo2.model.request.user.UserRequest;
import com.example.demo2.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toResponse(User user);

    User toEntity(UserRequest userRequest);

    void updateUserFromRequest(RegisterRequest userRequest, @MappingTarget User user);
}