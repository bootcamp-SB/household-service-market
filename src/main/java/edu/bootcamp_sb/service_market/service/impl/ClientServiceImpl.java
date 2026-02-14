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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static edu.bootcamp_sb.service_market.service.impl.ClientProfileServiceImpl.profileEntityTOClientProfileDto;
import static edu.bootcamp_sb.service_market.utill.UsernameSanitization.sanitizeUsername;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final KeyCloakUserHandleService keycloakUserHandleService;


    private final ObjectMapper mapper;

    public static ClientDto entityToClientDto(ClientEntity preEntity){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(preEntity.getId());
        clientDto.setAddress(preEntity.getAddress());
        clientDto.setEmail(preEntity.getEmail());
        clientDto.setPaymentMethod(preEntity.getPaymentMethod());
        clientDto.setCreateAt(preEntity.getCreateAt());
        clientDto.setUsername(preEntity.getUsername());
        clientDto.setFirstName(preEntity.getFirstName());
        clientDto.setLastName(preEntity.getLastName());
        clientDto.setUpdateAt(preEntity.getUpdateAt());
        clientDto.setContact(preEntity.getContact());
        return clientDto;

    }

    public static ClientResponseDto clientEntityToClientResponseDto(ClientEntity entity){

        return ClientResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .paymentMethod(entity.getPaymentMethod())
                .address(entity.getAddress())
                .createdAt(entity.getCreateAt())
                .profile(profileEntityTOClientProfileDto(entity.getProfile()))
                .contact(entity.getContact())
                .updateAt(entity.getUpdateAt())
                .build();
     }



    @Override
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<ClientResponseDto>> getAll() {

        Iterable<ClientEntity> allClients = clientRepository.findAll();

        ArrayList<ClientResponseDto> allClientList = new ArrayList<>();

        allClients.forEach(entity-> allClientList.add(
                clientEntityToClientResponseDto(entity)));

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

        //adding user to keycloak
        String userId = keycloakUserHandleService.createUser(
                clientDto.getUsername(),
                clientDto.getLastName(),
                clientDto.getFirstName(),
                clientDto.getEmail(),
                clientDto.getPassword(),
                "user"

        );


        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(UUID.fromString(userId));
        clientEntity.setUsername(sanitizeUsername(clientDto.getUsername()));
        clientEntity.setEmail(clientDto.getEmail());
        clientEntity.setFirstName(clientDto.getFirstName());
        clientEntity.setLastName(clientDto.getLastName());
        clientEntity.setAddress(clientDto.getAddress());
        clientEntity.setPaymentMethod(clientDto.getPaymentMethod());
        clientEntity.setCreateAt(LocalDate.now());
        clientEntity.setContact(clientDto.getContact());
        clientEntity.setUpdateAt(LocalDate.now());


        ClientProfileEntity profileEntity = new ClientProfileEntity();
        profileEntity.setProfilePicUrl(clientDto.getProfile().getProfilePicUrl());
        profileEntity.setClient(clientEntity);

        clientEntity.setProfile(profileEntity);
        ClientEntity saved = clientRepository.save(clientEntity);

        return ResponseEntity.ok(clientEntityToClientResponseDto(saved));

    }



    @Override
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResponseEntity<ClientResponseDto> updateByID(ClientRequestDto clientDto) {

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

        if(clientDto.getFirstName() != null){
            exsistingClientEntity.setFirstName(clientDto.getFirstName());
        }

        if(clientDto.getLastName() != null){
            exsistingClientEntity.setLastName(clientDto.getLastName());
        }

        if(clientDto.getUsername() != null){
            exsistingClientEntity.setUsername(clientDto.getUsername());
        }

        if(clientDto.getContact() != null){
            exsistingClientEntity.setContact(clientDto.getContact());
        }

        exsistingClientEntity.setUpdateAt(LocalDate.now());

        return ResponseEntity.ok().body(
                clientEntityToClientResponseDto(
                        clientRepository.save(exsistingClientEntity
                        ))
                );
    }

    @Override
    public ResponseEntity<ClientResponseDto> getById(String id) {
        ClientEntity clientEntity = clientRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new ClientHasBeenNotFoundException("client has been not found"));

        return ResponseEntity.ok(clientEntityToClientResponseDto(clientEntity));
    }


}
