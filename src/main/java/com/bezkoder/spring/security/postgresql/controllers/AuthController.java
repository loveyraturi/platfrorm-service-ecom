package com.bezkoder.spring.security.postgresql.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.postgresql.payload.request.CreateProductRequest;
import com.bezkoder.spring.security.postgresql.payload.request.UpdateProductRequest;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.RoleRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import com.bezkoder.spring.security.postgresql.security.jwt.JwtUtils;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ecom")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/productCreate")
	@PreAuthorize("hasRole('PLATFORM') or hasRole('ADMIN')")
	public ResponseEntity<?> productCreate(@Valid @RequestBody CreateProductRequest createProductRequest) {
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/productUpdate")
	@PreAuthorize("hasRole('USER') or hasRole('PLATFORM') or hasRole('ADMIN')")
	public ResponseEntity<?> productUpdate(@Valid @RequestBody UpdateProductRequest updateProductRequest) {
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
