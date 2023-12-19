package com.yoga.yoga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yoga.yoga.Entities.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

    public List<User> findByEmail(String email);

    @Query("select u from User u where u.email = :email")
    public User getUserByUserName(@Param("email") String email);
}
