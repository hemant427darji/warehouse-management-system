package com.example.warehouse.service.impl;


import com.example.warehouse.dto.mapper.ClientMapper;
import com.example.warehouse.dto.request.ClientRequest;
import com.example.warehouse.dto.response.ClientResponse;
import com.example.warehouse.entity.Client;
import com.example.warehouse.repository.ClientRepository;
import com.example.warehouse.service.contract.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Override
    @Transactional
    public ClientResponse registerClient(ClientRequest request) {
       String apiKey = UUID.randomUUID().toString();
       String secretKey = Base64.getEncoder().encodeToString(new SecureRandom().generateSeed(32));

      String encodedSecretKey = passwordEncoder.encode(secretKey);

       Client client = new Client();
       client.setEmail(request.email());
       client.setOrganizationName(request.OrganizationName());
       client.setApiKey(apiKey);
       client.setSecretKey(encodedSecretKey);

       clientRepository.save(client);
       return clientMapper.toResponse(client, secretKey);
    }
}
