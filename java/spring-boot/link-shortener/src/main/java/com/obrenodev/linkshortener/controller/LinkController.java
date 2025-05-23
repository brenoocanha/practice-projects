package com.obrenodev.linkshortener.controller;

import com.obrenodev.linkshortener.model.Link;
import com.obrenodev.linkshortener.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping()
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/url/{url}")
    public Optional<Link> getLinkByUrl(@PathVariable String url) {
        return linkService.getLinkByUrl(url);
    }

    @GetMapping("/{url}")
    public RedirectView getLink(@PathVariable String url) {
        Optional<Link> linkOptional = linkService.getLinkByEncodedUrl(url);
        if (linkOptional.isPresent()) {
            String originalUrl = linkOptional.get().getUrl();
            return new RedirectView("https://" + originalUrl);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Link> createLink(@RequestBody Link link) {
        Link createdLink = linkService.createLink(link);
        return new ResponseEntity<>(createdLink, HttpStatus.CREATED);
    }
}
