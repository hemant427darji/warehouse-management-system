package com.example.warehouse.security.filters;

import com.example.warehouse.entity.Client;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.exceptions.ClientNotFoundByApiKeyException;
import com.example.warehouse.repository.ClientRepository;
import com.example.warehouse.security.AuthUtilities;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Getter
@AllArgsConstructor
@Component
public class ClientAuthFilter extends OncePerRequestFilter {
    
    private final ClientRepository clientRepository;
    
    private static final String X_API_KEY = "X-Api-Key";
    private static final String X_SECRET_KEY ="X-Secret-Key";
    private PasswordEncoder passwordEncoder;

    
    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader(X_API_KEY);
        String secretKey = request.getHeader(X_SECRET_KEY);
       
       if(isValid(apiKey) && isValid(secretKey)){
         Client client = clientRepository.findByApiKey(apiKey).orElseThrow(()->new ClientNotFoundByApiKeyException("Client Not Found!!"));
         boolean doesMatch =  passwordEncoder.matches(secretKey,client.getSecretKey());

         if(doesMatch && AuthUtilities.getAuthentication().isEmpty()){
             final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(client.getOrganizationName(), null, List.of(new SimpleGrantedAuthority(UserRole.CLIENT.name())));
             AuthUtilities.setAuthentication(token);
         }
       }
       filterChain.doFilter(request,response);
    }

    private static boolean isValid(String s) {
        return s != null && !s.isBlank();
    }
}
