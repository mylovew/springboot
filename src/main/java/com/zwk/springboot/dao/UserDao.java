package com.zwk.springboot.dao;

import com.zwk.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByName(String name);
    @Query(value = "select user.*,role.role_id,role.role from user left join user_role on user_role.user_id = user.user_id left join role on role.role_id = user_role.role_id ORDER BY user.user_id ASC  limit ?1,?2",nativeQuery = true)
    List<Map<String,Object>> findByPage(Integer start, Integer limit);
    @Query(value = "select count(*) from User",nativeQuery = true)
    Long getCount();
    @Transactional
    void deleteByUserId(Integer user_id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE user u set " +
            "u.name = CASE WHEN :#{#user.name} IS NULL THEN u.name ELSE :#{#user.name} END," +
            "u.password = CASE WHEN :#{#user.password} IS NULL THEN u.password ELSE :#{#user.password} END," +
            "u.phone = CASE WHEN :#{#user.phone} IS NULL THEN u.phone ELSE :#{#user.phone} END," +
            "u.state = CASE WHEN :#{#user.state} IS NULL THEN u.state ELSE :#{#user.state} END " +
            "where u.user_id = :#{#user.userId} " , nativeQuery = true)
    int updByUserId(User user);

}
