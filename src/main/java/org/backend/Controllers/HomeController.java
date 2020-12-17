package org.backend.Controllers;


import org.backend.DTOs.TransferDTO;
import org.backend.Models.Account;
import org.backend.Reporitory.AccountRepository;
import org.backend.Reporitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HomeController {
    UserRepository userRepository;
    AccountRepository accountRepository;


    @Autowired
    public HomeController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/home/{account_id}")
    public Account getAccount(@PathVariable Long account_id) {
        if (accountRepository.findById(account_id).isPresent()) {
            return accountRepository.findById(account_id).get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "/transfer")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> transfer(@RequestBody(required = false) TransferDTO transferDTO, HttpServletRequest httpServletRequest) {

        Long myAccountId;
        Account accountByIBAN = accountRepository.findAccountByIBAN(transferDTO.getIBAN());
        Principal principal = httpServletRequest.getUserPrincipal();
        if (userRepository.findUserWithName(principal.getName()).isPresent()) {
            myAccountId = userRepository.findUserWithName(principal.getName()).get().getId();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Plese Log in");
        }
        Optional<Account> accountRepositoryById = accountRepository.findById(myAccountId);
        if (transferDTO.getBill() > accountRepository.findById(myAccountId).get().getBill()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("False IBAN or Bill 😔");
        } else {
            accountRepositoryById.ifPresent(account -> account.setBill(account.getBill() - transferDTO.getBill()));
        }

        if (accountByIBAN != null) {
            accountByIBAN.setBill(accountByIBAN.getBill() + transferDTO.getBill());

            System.out.println("New bill:" + accountByIBAN.getBill());

            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("False IBAN or Bill");
        }


    }

}
