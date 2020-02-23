package com.mohdasha.security.repository;

import com.mohdasha.security.entity.TOTPDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TOTPDetailsRepository extends MongoRepository<TOTPDetails, String> {
    TOTPDetails findByUsername(String username);
}
