package service;

import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.service.ClientService;
import edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;



    @Test
    void testGetClientById_shouldExists(){

        ClientEntity mockClient = new ClientEntity();
        mockClient.setId(UUID.fromString("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e"));
        mockClient.setUsername("John");
        when(clientRepository.findById
                (UUID.fromString("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e")))
                .thenReturn(Optional.of(mockClient));

        // Act
        ResponseEntity<ClientResponseDto> clientServiceById = clientService.getById("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e");

        // Assert
        assertEquals("John", clientServiceById.getBody().getUsername());

    }

}
