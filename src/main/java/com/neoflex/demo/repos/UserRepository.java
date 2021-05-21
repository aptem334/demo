package com.neoflex.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.neoflex.demo.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {

}