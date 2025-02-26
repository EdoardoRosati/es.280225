package com.example.es280225.repository;


import com.example.es280225.object.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
