package org.harvanir.oauth2.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Harvan Irsyadi
 */
@RestController
@RequestMapping("/secure/user")
public class SecureUserController {

    @GetMapping("/info")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<Principal> info(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
