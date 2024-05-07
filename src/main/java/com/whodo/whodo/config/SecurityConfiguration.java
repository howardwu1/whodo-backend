package com.whodo.whodo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
  

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         return httpSecurity
//         .csrf(csrf -> csrf.disable())
//         .authorizeHttpRequests(auth -> auth
//             .requestMatchers("/token/**").permitAll().requestMatchers("/hello").permitAll()
//             .anyRequest().authenticated()
//         )
//         .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//         .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
//         .httpBasic(Customizer.withDefaults())
//         .build();
//     }
// }
/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.core.userdetails.User;
 import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
 import org.springframework.security.web.SecurityFilterChain;
 
 import static org.springframework.security.config.Customizer.withDefaults;
 
 /**
  * An example of explicitly configuring Spring Security with the defaults.
  *
  * @author Rob Winch
  */
 @Configuration
 @EnableWebSecurity
 public class SecurityConfiguration {
 
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         // @formatter:off
         http
                 .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/token/**").permitAll().requestMatchers("/hello").permitAll()
                        .anyRequest().authenticated()
                 )
                 .httpBasic(withDefaults())
                 .formLogin(withDefaults());
         // @formatter:on
         return http.build();
     }
 
     // @formatter:off
     @Bean
     public InMemoryUserDetailsManager userDetailsService() {

        // PasswordEncoder encoder = 
        PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
        // remember the password that is printed out and use in the next step
        // System.out.println(encoder.encode("password"));

        UserDetails user = User.withUsername("user")
            .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")
            .roles("USER")
            .build();

         return new InMemoryUserDetailsManager(user);
     }
     // @formatter:on
 
 }