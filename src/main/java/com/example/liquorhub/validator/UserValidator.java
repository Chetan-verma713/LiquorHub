package com.example.liquorhub.validator;

import com.example.liquorhub.dto.UserDto;
import com.example.liquorhub.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class UserValidator {

    public static void validateCreateUser(UserDto userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || !userDTO.getEmail().contains("@gmail.com")) {
            throw new IllegalArgumentException("Email cannot be null or empty or invalid");
        }
        validateUser(userDTO);
    }

    private static void validateUser(UserDto userDTO) {
        if (userDTO.getId() != null) {
            throw new IllegalArgumentException("User ID should not be provided");
        }
        if (userDTO.getName() == null || userDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (userDTO.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        validateUserAge(userDTO);
        if (userDTO.getAddress() == null || userDTO.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        if (userDTO.getPhoneNumber() == null || userDTO.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
    }

    private static void validateUserAge(UserDto userDTO) {
        LocalDate dob = userDTO.getDateOfBirth()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int age = Period.between(dob, LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old.");
        }
    }

    public static void validateUpdateUser(UserDto userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }
        if (userDTO.getEmail() != null) {
            throw new IllegalArgumentException("Email cannot be changed");
        }
        validateUser(userDTO);
    }

    public static void validateUser(Long id, User user) {
        if (user == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    public static void validateExistingUser(User existingUser, String email) {
        if (existingUser != null) {
            throw new DataIntegrityViolationException("User already exists with email: " + email);
        }
    }
}
