package com.example.warehouse.security;
import com.example.warehouse.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable());

        //authorization of endpoints as private and public
        http.authorizeHttpRequests(auth-> auth.requestMatchers("/users")
                .permitAll()
                .requestMatchers("/warehouses").hasAuthority(UserRole.ADMIN.name())
                .anyRequest().authenticated());

        //type of authentication to user [HttBasic , FormLogin, AuthOLogin]
        http.formLogin(Customizer.withDefaults());

        //building
       return http.build();
    }
}
