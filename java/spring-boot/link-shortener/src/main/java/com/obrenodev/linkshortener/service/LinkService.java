package com.obrenodev.linkshortener.service;

import com.obrenodev.linkshortener.model.Link;
import com.obrenodev.linkshortener.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public Optional<Link> getLinkByUrl(String url) {
        return linkRepository.findByUrl(url);
    }

    public Optional<Link> getLinkByHashedUrl(String hashedUrl) {
        return linkRepository.findByHashedUrl(hashedUrl);
    }

    public Link createLink(Link link) {
        Optional<Link> existingLink = linkRepository.findByUrl(link.getUrl());
        if (existingLink.isPresent()) return existingLink.get();

        String shortCode = generateHashedUrl(link.getUrl());
        link.setHashedUrl(shortCode);

        return linkRepository.save(link);
    }

    private String generateHashedUrl(String url) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(url.getBytes());

            String base64Encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);

            return base64Encoded.substring(0, Math.min(base64Encoded.length(), 7));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
