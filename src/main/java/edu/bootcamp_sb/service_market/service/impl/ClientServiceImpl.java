package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
import edu.bootcamp_sb.service_market.exception.clientExceptions.ClientAlreadyRegisteredException;
import edu.bootcamp_sb.service_market.exception.clientExceptions.ClientHasBeenNotFoundException;

import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ObjectMapper mapper;



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
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResponseEntity<ClientResponseDto> persist(ClientRequestDto clientDto) {

        Optional<ClientEntity> byEmail =
                clientRepository.findByEmail(clientDto.getEmail());

        if(byEmail.isPresent()) throw new ClientAlreadyRegisteredException
                ("Email has been registered before");


        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setEmail(clientDto.getEmail());
        clientEntity.setAddress(clientDto.getAddress());
        clientEntity.setPaymentMethod(clientDto.getPaymentMethod());


        ClientProfileEntity profileEntity = new ClientProfileEntity();
        profileEntity.setProfilePicUrl(clientDto.getProfile().getProfilePicUrl());
        profileEntity.setClient(clientEntity);

        profileEntity.setClient(clientEntity);

        clientEntity.setProfile(profileEntity);


        return ResponseEntity.ok().body(
                mapper.convertValue(
                                clientRepository.save(clientEntity),
                        ClientResponseDto.class
                )
        );
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
