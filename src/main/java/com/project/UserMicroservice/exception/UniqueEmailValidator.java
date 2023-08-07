package com.project.UserMicroservice.exception;

import com.project.UserMicroservice.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;



public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserRepository userRepository;

    public UniqueEmailValidator() {
    }


    @Autowired
    public UniqueEmailValidator(UserRepository userRepository) {
            this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueEmail annotation) {}

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.existsByEmail(email);
    }
}
