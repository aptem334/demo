package com.aptem334.demo.Repository;

import com.aptem334.demo.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    List<Accounts> findByOwner(@Param("owner") Integer owner);
}
