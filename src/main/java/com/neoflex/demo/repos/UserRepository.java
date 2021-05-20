package com.neoflex.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.neoflex.demo.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}