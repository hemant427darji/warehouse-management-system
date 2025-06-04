package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.ClientRequest;
import com.example.warehouse.dto.response.ClientResponse;
import com.example.warehouse.entity.Client;
import org.springframework.stereotype.Controller;

@Controller
public class ClientMapper {

    public Client toEntity(ClientRequest source, Client target){
        target.setOrganizationName(source.OrganizationName());
        target.setEmail(source.email());
        return target;
    }

    public ClientResponse toResponse(Client client, String rawSecretKey){
        return new ClientResponse(
                client.getClientId(),
                client.getOrganizationName(),
                client.getEmail(),
                client.getApiKey(),
                rawSecretKey,
                client.getRegisterAt().toEpochMilli()
        );
    }
}
