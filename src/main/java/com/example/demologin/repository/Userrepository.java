package com.example.demologin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demologin.domain.User;

@Repository
public interface Userrepository extends JpaRepository<User, String> {
User findByUsernameAndPassword(String username, String password);
}
