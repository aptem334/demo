package com.aptem334.demo.repos;

import org.springframework.data.repository.CrudRepository;
import com.aptem334.demo.Users;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Integer> {
    List<Users> findByEmailOrName(String email, String name);
}