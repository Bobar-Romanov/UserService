package com.project.UserMicroservice.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(name = "surname")
    @NotBlank
    @Size(max = 100)
    private String surname;

    @Column(name = "email", unique = true)
    @NotBlank
    @Email
    //@UniqueEmail
    @Size(max = 255)
    private String email;

    @Column(name = "birthdate")
    @Past
    private LocalDate birthDate;

    @Column(name = "role")
    @Size(max = 50)
    private String role;

}
