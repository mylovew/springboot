package com.zwk.springboot.dao;

import com.zwk.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByName(String name);
}
