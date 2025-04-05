package com.example.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                                .requestMatchers("/search").permitAll() // ✅ Allow public access to search API
//                                .requestMatchers(HttpMethod.POST,"/products").authenticated() // ✅ Allow public access to product list
//                                //.requestMatchers("/products").permitAll() // ✅ Allow public access to product list
//                                .requestMatchers("/products/{id}").permitAll() // 🔒 Keep authentication for product details
////                        //.authenticated()
////                        .hasAuthority("SCOPE_ADMIN")
//                       // .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
  //  }
}
