package com.example.liquorhub.repository;

import com.example.liquorhub.entity.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

}

