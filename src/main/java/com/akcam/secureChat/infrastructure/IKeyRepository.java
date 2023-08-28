package com.akcam.secureChat.infrastructure;

import com.akcam.secureChat.domain.key.SessionKey;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface IKeyRepository extends MongoRepository<SessionKey, UUID> {

}


