package com.example.demo2.mapper;

import com.example.demo2.entity.authentication.User;
import com.example.demo2.model.request.authentication.UserRequest;
import com.example.demo2.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "idUser", target = "idUser")
    UserResponse toResponse(User user);

    User toEntity(UserRequest userRequest);

    void updateUserFromRequest(UserRequest userRequest, @MappingTarget User user);
}