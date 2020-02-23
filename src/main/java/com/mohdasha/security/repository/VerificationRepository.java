package com.mohdasha.security.repository;

import com.mohdasha.security.entity.Verification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends MongoRepository<Verification, String> {
    Verification findByUsername(String username);
}
