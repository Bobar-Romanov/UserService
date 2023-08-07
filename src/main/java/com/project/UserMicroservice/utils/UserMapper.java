package com.project.UserMicroservice.utils;

import com.project.UserMicroservice.dto.UserCreationDTO;
import com.project.UserMicroservice.dto.UserDTO;
import com.project.UserMicroservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toUser(UserCreationDTO userCreationDTO) {
        return modelMapper.map(userCreationDTO, User.class);
    }

    public UserDTO toUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}

