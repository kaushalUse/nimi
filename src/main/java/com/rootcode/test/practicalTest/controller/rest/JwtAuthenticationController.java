package com.rootcode.test.practicalTest.controller.rest;

import com.rootcode.test.practicalTest.dto.JwtRequest;
import com.rootcode.test.practicalTest.dto.JwtResponse;
import com.rootcode.test.practicalTest.service.UserDetailsServiceImpl;
import com.rootcode.test.practicalTest.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @RequestMapping(value = "/create-token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)  {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}