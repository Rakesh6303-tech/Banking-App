package com.example.banking.controller;


import com.example.banking.dto.AccountDto;
import com.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController  // Makes Class Has A Spring MVC as A class
@RequestMapping("/api/accounts") //
public class AccountController {

    // Injected Dependencies
    private AccountService accountService;
    // Injected Constructor Based Dependencies
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ADD Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable  Long id){
        AccountDto accountDto = accountService.getAccontById(id);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody
                                              Map<String, Double> request){
        Double amount = request.get("amount");
       AccountDto accountDto =  accountService.deposit(id,request.get("amount"));
       return ResponseEntity.ok(accountDto);
    }

    // WithDraw REST API
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                                @RequestBody  Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return  ResponseEntity.ok(accountDto);
    }

    // GET ALL ACCOUNTS REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is Deleted Successfully");
    }
}
