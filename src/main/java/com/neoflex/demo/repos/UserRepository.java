package com.neoflex.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.neoflex.demo.Entity;

public interface UserRepository extends CrudRepository<Entity, Integer> {

}