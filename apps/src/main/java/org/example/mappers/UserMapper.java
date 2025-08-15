package org.example.mappers;

import org.example.requests.RegisterUserRequest;
import org.example.requests.UserRequest;
import org.example.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserRequest toDto(User user); //auto impl new UserDto based on the user passed in
    User toEntity(RegisterUserRequest request);
}
