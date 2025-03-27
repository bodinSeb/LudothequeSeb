package com.eni.ludotheque2.WebSecurityControl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecuConf {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                                //ATTENTIONL'ordre des permissions est importante
                                .requestMatchers("/api/utilisateurs").permitAll()//hasAnyRole("admin")
                                .requestMatchers("/api/clients/**", "/api/factures/**").hasAnyRole("admin", "employe")
                                .requestMatchers("/api/login").permitAll()
                                //.requestMatchers("/", "/home").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER") //permission
                                //.requestMatchers("/", "/home/", "/public/**").permitAll() //pas besoin d'être connecté
                                //.requestMatchers("/", "/home").hasAnyRole("ROLE_ADMIN", "ROLE_USER") //au-dessus permission
                                //.requestMatchers("/private/**").permitAll() //Probleme : Viens après

                                //Les routes restantes
                                .anyRequest().authenticated()
                        //.anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults());
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance(); //sans gestion du chiffrement
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //on peut changer l'algorithme de chiffrement au cours de la vie de l'app
    }
}
