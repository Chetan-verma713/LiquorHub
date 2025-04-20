package com.example.liquorhub.security;

import com.example.liquorhub.entity.User;

public class WebUtils {

    public static User getCurrentUser() {
        // Implement logic to retrieve the current user from the security context
        // This is just a placeholder implementation
        User user = new User();
        user.setId(1L); // Example user ID
        return user;
    }
}
