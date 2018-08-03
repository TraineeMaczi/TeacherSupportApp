package com.nokia.teachersupport.personSecurity.personRegister.verificationToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepo extends CrudRepository<VerificationToken, Integer> {
    List<VerificationToken> findAll();
    VerificationToken findByToken(String token);
}

