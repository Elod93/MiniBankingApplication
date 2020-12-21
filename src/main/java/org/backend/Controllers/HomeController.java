package org.backend.Controllers;


import org.backend.DTOs.AccountDTO;
import org.backend.DTOs.TransferDTO;
import org.backend.DTOs.UserDTO;
import org.backend.Models.Account;
import org.backend.Models.User;
import org.backend.Reporitory.AccountRepository;
import org.backend.Reporitory.UserRepository;
import org.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
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


    @Autowired
    public HomeController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/home/{account_id}")
    public List<Account> getAccount(@PathVariable Long account_id) {
        if (accountRepository.findById(account_id).isPresent()) {
            return accountRepository.findById(account_id).stream().collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @PostMapping(value = "/transfer")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> transfer(@Valid @RequestBody(required = false) TransferDTO transferDTO, HttpServletRequest httpServletRequest) {

        Long myAccountId;
        Account accountByIBAN = accountRepository.findAccountByIBAN(transferDTO.getIBAN());
        Principal principal = httpServletRequest.getUserPrincipal();
        if (userRepository.findUserWithName(principal.getName()).isPresent()) {
            myAccountId = userRepository.findUserWithName(principal.getName()).get().getId();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Please Log in");
        }
        Optional<Account> accountRepositoryById = accountRepository.findById(myAccountId);
        if (transferDTO.getBill() > accountRepository.findById(myAccountId).get().getBalance()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("False IBAN or Bill 😔");
        } else {
            accountRepositoryById.ifPresent(account -> account.setBalance(account.getBalance() - transferDTO.getBill()));
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
    public ResponseEntity<List<UserDTO>> searchByParams(@RequestBody AccountDTO accountDTO){
        List<User> response=userService.findUserByParams(accountDTO);
       List<UserDTO > userDTOList= new ArrayList<>();
        if (!response.isEmpty()){
            for (User user : response) {
                UserDTO userDTO=new UserDTO();
                userDTO.setName(user.getFullName());
                userDTO.setAddress(user.getPostAddress());
               if(userRepository.findUserWithName(user.getUsername()).isPresent()){
                   List<Account> account = userRepository.findById(user.getId()).get().getAccount();
                   for (Account account1 : account) {
                       String IBAN="";
                       IBAN=account1.getIBAN();
                       userDTO.setIBAN(IBAN);
                   }

               }
                userDTO.setMessage("Success");
                userDTOList.add(userDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
        }else {
            UserDTO userDTO=new UserDTO();
            userDTO.setMessage("Fail");
            userDTOList.add(userDTO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userDTOList);
        }

    }

}
