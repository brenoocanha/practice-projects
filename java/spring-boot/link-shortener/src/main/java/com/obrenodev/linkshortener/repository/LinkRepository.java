package com.obrenodev.linkshortener.repository;

import com.obrenodev.linkshortener.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {
    Optional<Link> findByUrl(String url);

    Optional<Link> findByEncodedUrl(String encodedUrl);
}
