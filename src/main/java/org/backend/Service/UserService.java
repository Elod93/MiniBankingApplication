package org.backend.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.backend.DTOs.AccountDTO;
import org.backend.Models.Account;
import org.backend.Models.QAccount;
import org.backend.Models.QUser;
import org.backend.Models.User;
import org.backend.Reporitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
    public List<User> findUserByParams(AccountDTO accountDTO) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (StringUtils.isNotBlank(accountDTO.getName())) {
            booleanBuilder.and(QUser.user.fullName.like(accountDTO.getName()));
        }

        if (StringUtils.isNotBlank(accountDTO.getIban())) {
            booleanBuilder.and(QUser.user.account.any().IBAN.like(accountDTO.getIban()));
        }
        if(accountDTO.getPostAddress()!=null) {
            if (StringUtils.isNotBlank(accountDTO.getPostAddress().getCityName())) {
                booleanBuilder.and(QUser.user.postAddress.cityName.like(accountDTO.getPostAddress().getCityName()));
            }
            if (StringUtils.isNotBlank(accountDTO.getPostAddress().getStreetName())) {
                booleanBuilder.and(QUser.user.postAddress.streetName.like(accountDTO.getPostAddress().getStreetName()));
            }
            if (accountDTO.getPostAddress().getPostCode() != null) {
                booleanBuilder.and(QUser.user.postAddress.postCode.eq(accountDTO.getPostAddress().getPostCode()));
            }
            if (accountDTO.getPostAddress().getHouseNumber() != null) {
                booleanBuilder.and(QUser.user.postAddress.houseNumber.eq(accountDTO.getPostAddress().getHouseNumber()));
            }
        }

        return queryFactory.selectFrom(QUser.user)
                .where(booleanBuilder)
                .fetch();
    }

}