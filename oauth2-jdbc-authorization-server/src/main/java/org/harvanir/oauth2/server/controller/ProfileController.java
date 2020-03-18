package org.harvanir.oauth2.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Harvan Irsyadi
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/info")
    public ResponseEntity<Principal> info(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
