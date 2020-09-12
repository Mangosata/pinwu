package com.pinwu.auth.repository;

import com.pinwu.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bin Jia
 * @date 2020/9/6 0:41
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  User getUserByUsernameAndPassword(String username, String password);

  User getUserByUsername(String username);

  User getUserByEmail(String email);

}
