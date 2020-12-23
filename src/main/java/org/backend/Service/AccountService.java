package org.backend.Service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class AccountService {
    @PersistenceContext
    EntityManager em;
    @Transactional
    public void saveInAccountHistory(){

    }
}
