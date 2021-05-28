package com.aptem334.demo.Repository;

import com.aptem334.demo.Model.Accounts;
import com.aptem334.demo.Model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    List<Accounts> findByOwner(@Param("owner") Integer owner);
    Optional<Accounts> findByAccountNumberAndOwner_id(Integer accountNumber, Integer owner_id);
    List<Accounts> findByOwner_id(Integer owner_id);
}
