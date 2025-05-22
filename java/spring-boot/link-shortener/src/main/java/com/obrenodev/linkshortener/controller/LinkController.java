package com.obrenodev.linkshortener.controller;

import com.obrenodev.linkshortener.model.Link;
import com.obrenodev.linkshortener.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/url/{url}")
    public Optional<Link> getLinkByUrl(@PathVariable String url) {
        return linkService.getLinkByUrl(url);
    }

    @GetMapping("/{url}")
    public Optional<Link> getLink(@PathVariable String url) {
        return linkService.getLinkByHashedUrl(url);
    }

    @PostMapping
    public ResponseEntity<Link> createLink(@RequestBody Link link) {
        Link createdLink = linkService.createLink(link);
        return new ResponseEntity<>(createdLink, HttpStatus.CREATED);
    }
}
