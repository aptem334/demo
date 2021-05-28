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

    @PostMapping("/user/{id}/accounts")
    public Accounts addAccount(@PathVariable("id") Integer id,
                               @Valid @RequestBody Accounts account) {
        return userRepository.findById(id).map(users -> {
            account.setOwner(users);
            return accountRepository.save(account);
        }).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
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
