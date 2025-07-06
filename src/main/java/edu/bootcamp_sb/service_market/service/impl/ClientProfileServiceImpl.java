package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.ClientProfileDto;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
import edu.bootcamp_sb.service_market.exception.clientExceptions.ClientHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.ClientProfileRepository;
import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.service.ClientProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientProfileServiceImpl implements ClientProfileService {

    private final ClientProfileRepository profileRepository;

    private final ObjectMapper mapper;

    private final ClientRepository clientRepository;

    @Override
    public ResponseEntity<ClientProfileDto> create(ClientProfileDto profile) {
        ClientProfileEntity profileEntity = new ClientProfileEntity();
        profileEntity.setProfilePicUrl(profile.getProfilePicUrl());

        return ResponseEntity.ok(
                mapper.convertValue(profileRepository.save(profileEntity)
                        ,ClientProfileDto.class)
        );
    }

    @Override
    public ResponseEntity<List<ClientProfileDto>> getAll() {
        Iterable<ClientProfileEntity> profileEntities = profileRepository.findAll();
        ArrayList<ClientProfileDto> profileList = new ArrayList<>();

        profileEntities.forEach(profileEntity->profileList.add(
                mapper.convertValue(profileEntity,
                        ClientProfileDto.class)
        )
        );
        return ResponseEntity.ok(profileList);
    }
}
