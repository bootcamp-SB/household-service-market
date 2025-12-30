package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
import edu.bootcamp_sb.service_market.exception.client_exceptions.ClientAlreadyRegisteredException;
import edu.bootcamp_sb.service_market.exception.client_exceptions.ClientHasBeenNotFoundException;

import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.service.ClientService;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import edu.bootcamp_sb.service_market.utill.UsernameSanitization;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

import static edu.bootcamp_sb.service_market.service.impl.ClientProfileServiceImpl.profileEntityTOClientProfileDto;
import static edu.bootcamp_sb.service_market.utill.UsernameSanitization.sanitizeUsername;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final KeyCloakUserHandleService userHandleService;


    private final ObjectMapper mapper;

    public static ClientDto entityToClientDto(ClientEntity preEntity){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(preEntity.getId());
        clientDto.setAddress(preEntity.getAddress());
        clientDto.setEmail(preEntity.getEmail());
        clientDto.setPaymentMethod(preEntity.getPaymentMethod());

        return clientDto;

    }



    @Override
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<ClientResponseDto>> getAll() {

        Iterable<ClientEntity> allClients = clientRepository.findAll();

        ArrayList<ClientResponseDto> allClientList = new ArrayList<>();

        allClients.forEach(entity-> allClientList.add(
                mapper.convertValue(
                        entity
                        ,ClientResponseDto.class)));

        return ResponseEntity.ok().body(allClientList);

    }


    @Override
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResponseEntity<Map<String, String>> deleteById(UUID id) {
        if(!clientRepository.existsById(id)){
            throw new ClientHasBeenNotFoundException("Incorrect client id");
        }
        clientRepository.deleteById(id);
        return ResponseEntity.ok().body(Map.of
                ("Deletion message","Has been successfully deleted")
        );
    }

    @Override
    public ResponseEntity<ClientResponseDto> persist(ClientRequestDto clientDto) {

        Optional<ClientEntity> byEmail =
                clientRepository.findByEmail(clientDto.getEmail());

        if (byEmail.isPresent()) {
            throw new ClientAlreadyRegisteredException(
                    "Email has been registered before"
            );
        }

        ResponseEntity<String> userId = userHandleService.createUser(
                clientDto.getUsername(),
                clientDto.getLastName(),
                clientDto.getFirstName(),
                clientDto.getUsername()
        );

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUsername(sanitizeUsername(clientDto.getUsername()));
        clientEntity.setEmail(clientDto.getEmail());
        clientEntity.setFirstName(clientDto.getFirstName());
        clientEntity.setLastName(clientDto.getLastName());
        clientEntity.setAddress(clientDto.getAddress());
        clientEntity.setPaymentMethod(clientDto.getPaymentMethod());
        clientEntity.setKeycloakId(String.valueOf(userId));


        ClientProfileEntity profileEntity = new ClientProfileEntity();
        profileEntity.setProfilePicUrl(clientDto.getProfile().getProfilePicUrl());
        profileEntity.setClient(clientEntity);

        clientEntity.setProfile(profileEntity);
        ClientEntity saved = clientRepository.save(clientEntity);


        ClientResponseDto responseDto = ClientResponseDto.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .address(saved.getAddress())
                .paymentMethod(saved.getPaymentMethod())
                .profile(profileEntityTOClientProfileDto(saved.getProfile()))
                .build();
        return ResponseEntity.ok(responseDto);

    }



    @Override
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResponseEntity<ClientDto> updateByID(ClientDto clientDto) {

        Optional<ClientEntity> optionalEntity = clientRepository.findById(clientDto.getId());

        if (optionalEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ClientEntity exsistingClientEntity = optionalEntity.get();

        if(clientDto.getEmail() != null){
            exsistingClientEntity.setEmail(clientDto.getEmail());
        }
        if(clientDto.getAddress() != null){
            exsistingClientEntity.setAddress(clientDto.getAddress());
        }
        if(clientDto.getPaymentMethod() !=null){
            exsistingClientEntity.setPaymentMethod(clientDto.getPaymentMethod());
        }

        return ResponseEntity.ok().body(
                mapper.convertValue(
                        clientRepository.save(exsistingClientEntity
                        ),ClientDto.class)
                );
    }


}
