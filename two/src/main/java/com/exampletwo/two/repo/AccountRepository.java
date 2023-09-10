package com.exampletwo.two.repo;

import com.exampletwo.two.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
