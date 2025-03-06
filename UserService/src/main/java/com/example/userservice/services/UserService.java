package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repositories.TokenRepository;
import com.example.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Token login(String email, String password) {
        Optional<User> userOp = userRepository.findByEmail(email);
        if (userOp.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOp.get();
        if (!passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new UsernameNotFoundException("Password is incorrect");
        }
        Token token = generateToken(user);
        return tokenRepository.save(token);


    }

    private Token generateToken(User user) {
    Token token = new Token();
    token.setValue(RandomStringUtils.randomAlphanumeric(10));
    token.setExpiryAt(System.currentTimeMillis() + 3600000);
    token.setUser(user);
    return token;
    }
    public User validateToken(String token) {
        Optional<Token> tokenOp = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token, false, System.currentTimeMillis());
        if (tokenOp.isEmpty()) {
            throw new UsernameNotFoundException("Token not found");
        }
        Token token1 = tokenOp.get();
        return token1.getUser();
    }


}
