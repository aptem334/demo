package com.aptem334.demo.Repository;

import com.aptem334.demo.Model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Integer> {
    List<Users> findByEmailOrNameOrPhone(String email, String name, String phone);
}