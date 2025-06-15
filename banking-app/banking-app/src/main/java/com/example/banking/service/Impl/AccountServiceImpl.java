package com.example.banking.service.Impl;

import com.example.banking.Exception.AccountException;
import com.example.banking.Mapper.AccountMapper;
import com.example.banking.Reposistory.AccountReposistory;
import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Spring 4.3  we can ignore @AutoWired
// Bcz Whenever Spring will find single Constrututor in Spring Bean . Then Automaticlly inject Dependencies
@Service  // Automatically Created Beans
public class AccountServiceImpl implements AccountService {

    // Inject The Dependencie
    private AccountReposistory accountReposistory;

    // injected dependencies
    public AccountServiceImpl(AccountReposistory accountReposistory) {
        this.accountReposistory = accountReposistory;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountReposistory.save(account);
        // Call AccountReposistory and save into Db
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccontById(Long id) {
        Account account = accountReposistory
                .findById(id).
                orElseThrow(() -> new AccountException("Account Does't Exists"));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountReposistory
                .findById(id).
                orElseThrow(() -> new AccountException("Account Does't Exists"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountReposistory.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
       // Checking account is in DB
        Account account = accountReposistory
                .findById(id).
                orElseThrow(() -> new AccountException("Account Does't Exists"));

        if(account.getBalance() < amount)
        {
            throw new RuntimeException("Insufficent Amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountReposistory.save(account);

        return AccountMapper.maptoAccountDto(savedAccount);


    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountReposistory.findAll();
        return accounts.stream().map((account) -> AccountMapper.maptoAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountReposistory
                .findById(id).
                orElseThrow(() -> new AccountException("Account Does't Exists"));
        accountReposistory.deleteById(id);

    }
}
