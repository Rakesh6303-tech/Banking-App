package com.example.banking.Reposistory;

import com.example.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountReposistory extends JpaRepository<Account, Long> {
}
