package com.project.UserMicroservice.service;

import com.project.UserMicroservice.exception.DuplicateEmailException;
import com.project.UserMicroservice.dto.UserCreationDTO;
import com.project.UserMicroservice.dto.UserDTO;
import com.project.UserMicroservice.model.User;
import com.project.UserMicroservice.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Slf4j
@Service
@Validated
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO addUser(@Valid UserCreationDTO userCreationDTO) {
        this.checkEmailUniqueness(userCreationDTO.getEmail());
        User user = modelMapper.map(userCreationDTO, User.class);
        userRepository.save(user);
        log.info("User saved: {}", user.getName());
        return modelMapper.map(user, UserDTO.class);
    }

    public void deleteUser(@Valid Long userId) {
        User user = this.getUserById(userId);
        userRepository.delete(user);
        log.info("User deleted: {}",userId);
    }

    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        log.info("Get user with id: {}", userId);
        return optionalUser.get();
    }

    public UserDTO getUser(Long userId){
        log.info("Try to get user with id: {}", userId);
        return modelMapper.map(this.getUserById(userId), UserDTO.class);
    }

    private void checkEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already exists: " + email);
        }
    }
}
