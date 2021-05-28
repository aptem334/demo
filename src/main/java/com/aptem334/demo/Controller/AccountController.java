package com.aptem334.demo.Controller;


import com.aptem334.demo.Model.Accounts;
import com.aptem334.demo.Model.Users;
import com.aptem334.demo.Repository.AccountRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.aptem334.demo.Repository.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/user/{id}/accounts")
//    public Page<Accounts> getAll(@PathVariable("id") Integer id,
//                                                  Pageable pageable) {
//        return accountRepository.findById(id, pageable);
//    }

    @GetMapping("/user/{id}/accounts")
    List<Accounts> getAccount(@PathVariable("id") Integer id) {
        return accountRepository.findByOwner_id(id);
    }

    @PostMapping("/user/{id}/accounts")
    public Accounts addAccount(@PathVariable("id") Integer id,
                               @Valid @RequestBody Accounts account) {
        return userRepository.findById(id).map(users -> {
            account.setOwner(users);
            return accountRepository.save(account);
        }).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    @PutMapping("/user/{id}/accounts/{account_id}")
    public Accounts updateAccount(@PathVariable("id") Integer id,
                                  @PathVariable("account_id") Integer accountNumber,
                                  @Valid @RequestBody Accounts accountRequest) {

        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        return accountRepository.findById(accountNumber).map(account -> {
            account.setAmount(accountRequest.getAmount());
            return accountRepository.save(account);
        }).orElseThrow(() -> new IllegalArgumentException("Invalid account Id:" + accountNumber));
    }

    @DeleteMapping("/user/{id}/accounts/{account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Integer id,
                                           @PathVariable("account_id") Integer accountNumber) {
        return accountRepository.findByAccountNumberAndOwner_id(accountNumber, id).map(account -> {
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Comment not found with account id " + accountNumber + " and user id " + id));
    }

    @GetMapping("/all")
    List<Accounts> all() {
        return accountRepository.findAll();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
