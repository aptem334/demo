package com.aptem334.demo.Repository;

import com.aptem334.demo.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, Integer> {

}
