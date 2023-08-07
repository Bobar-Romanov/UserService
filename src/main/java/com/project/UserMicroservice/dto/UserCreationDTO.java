package com.project.UserMicroservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String surname;

    @NotBlank
    @Email
    //@UniqueEmail
    @Size(max = 255)
    private String email;


    @Past
    @NotNull
    private LocalDate birthDate;

}
