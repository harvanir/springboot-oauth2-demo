package org.harvanir.oauth2.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * @author Harvan Irsyadi
 */
@RestController
@RequestMapping("/secure/user")
public class SecureUserController {

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    public ResponseEntity<Principal> info(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/info/authorities")
    @PreAuthorize("hasAuthority('READ_AUTHORITIES')")
    public ResponseEntity<List<String>> readAuthorities() {
        return ResponseEntity.ok(Arrays.asList("ROLE_ADMIN", "ROLE_CLIENT", "READ_AUTHORITIES", "READ_NAME"));
    }

    @GetMapping("/info/name")
    @PreAuthorize("hasAuthority('READ_NAME')")
    public ResponseEntity<String> readName(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }
}
