package com.aptem334.demo.Controller;


import com.aptem334.demo.Model.Accounts;
import com.aptem334.demo.Model.Users;
import com.aptem334.demo.Repository.AccountRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/add")
    ResponseEntity<String> addAccount (@Valid @RequestBody Accounts account) {
        accountRepository.save(account);
        return ResponseEntity.ok("Account is valid");
    }

    @GetMapping("/all")
    List<Accounts> all() {
        return accountRepository.findAll();
    }

    @PostMapping(path="/owner")
    public Iterable<Accounts> searchUsers(@JsonProperty("owner") Integer owner){
        return accountRepository.findByOwner(owner);
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
