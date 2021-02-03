package org.backend.Controllers;


import org.backend.DTOs.AccountDTO;
import org.backend.DTOs.TransferDTO;
import org.backend.DTOs.UserDTO;
import org.backend.Models.Account;
import org.backend.Models.History;
import org.backend.Models.User;
import org.backend.Reporitory.AccountRepository;
import org.backend.Reporitory.UserRepository;
import org.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HomeController {

    UserRepository userRepository;
    AccountRepository accountRepository;
    @Autowired
    UserService userService;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public HomeController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/transfer/{username}")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> transfer(@PathVariable String username,@Valid @RequestBody(required = false) TransferDTO transferDTO) {
        History myAccountHistory = new History();
        History accountHistory = new History();
        Long myAccountId = null;
        Account accountByIBAN = accountRepository.findAccountByIBAN(transferDTO.getIBAN()); //the account where I send money
        if (userRepository.findUserWithName(username).isPresent()) {
            List<Account> myAccounts = userRepository.findUserWithName(username).stream().map(User::getAccount).collect(Collectors.toList())
                    .stream().flatMap(List::stream).collect(Collectors.toList());
            for (Account myAccount : myAccounts) {
                if (myAccount.getIBAN().equals(transferDTO.getMyIBAN())) { //choose the real account
                    myAccountId = myAccount.getId();
                    System.out.println(myAccountId);
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Please Log in");
        }
        assert myAccountId != null;
        Optional<Account> accountRepositoryById = accountRepository.findById(myAccountId);
        if (transferDTO.getBill() > accountRepository.findById(myAccountId).get().getBalance()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("False IBAN or Bill 😔");
        } else {
            //transferred money and save the details in  accounts history
            accountRepositoryById.ifPresent(account -> account.setBalance(account.getBalance() - transferDTO.getBill()));
            myAccountHistory.setAmount(accountRepository.findById(myAccountId).get().getBalance());
            myAccountHistory.setTransferredAmount(transferDTO.getBill());
            myAccountHistory.setDateTime(LocalDateTime.now());
            em.persist(myAccountHistory);
            accountRepository.findById(myAccountId).get().getHistories().add(myAccountHistory);

            accountHistory.setAmount(accountRepository.findAccountByIBAN(transferDTO.getIBAN()).getBalance());
            accountHistory.setCrediting(transferDTO.getBill());
            accountHistory.setDateTime(LocalDateTime.now());
            em.persist(accountHistory);
            accountRepository.findAccountByIBAN(transferDTO.getIBAN()).getHistories().add(accountHistory);
        }

        if (accountByIBAN != null) {
            accountByIBAN.setBalance(accountByIBAN.getBalance() + transferDTO.getBill());

            System.out.println("New bill:" + accountByIBAN.getBalance());

            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("False IBAN or Bill");
        }


    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<UserDTO>> searchByParams(@RequestBody AccountDTO accountDTO) {
        List<User> response = userService.findUserByParams(accountDTO);
        List<UserDTO> userDTOList = new ArrayList<>();
        if (!response.isEmpty()) {
            for (User user : response) {
                UserDTO userDTO = new UserDTO();
                userDTO.setName(user.getFullName());
                userDTO.setAddress(user.getPostAddress());
                if (userRepository.findUserWithName(user.getUsername()).isPresent()) {
                    List<Account> account = userRepository.findById(user.getId()).get().getAccount();
                    for (Account account1 : account) {
                        String IBAN = "";
                        IBAN = account1.getIBAN();
                        userDTO.setIBAN(IBAN);
                    }

                }
                userDTO.setMessage("Success");
                userDTOList.add(userDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setMessage("Fail");
            userDTOList.add(userDTO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userDTOList);
        }

    }

    @GetMapping(value = "/history/{account_id}")
    public List<History> getAccountHistory(@PathVariable Long account_id) {
        if (accountRepository.findById(account_id).isPresent()) {
            return accountRepository.findById(account_id).get().getHistories();
        } else {
            return null;
        }
    }
    @GetMapping(value = "/home/{username}")
    public List<Account> getAccount(@PathVariable String username) {
        if (userRepository.findUserWithName(username).isPresent()) {
            return (List<Account>) userRepository.findUserWithName(username).stream().map(User::getAccount).collect(Collectors.toList())
                    .stream().flatMap(List::stream).collect(Collectors.toList());
        } else {
            return null;
        }
    }
    @GetMapping(value = "/all_accounts")
    public List<Account> getAllAccount() {
       List<Account> accountList= new ArrayList<>();
       accountList=accountRepository.findAll();
       return accountList;
    }

}
