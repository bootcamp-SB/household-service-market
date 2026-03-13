package service;

import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.exception.client_exceptions.ClientHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.service.ClientService;
import edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl;
import edu.bootcamp_sb.service_market.service.impl.KeyCloakUserHandleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @InjectMocks
    private KeyCloakUserHandleServiceImpl keyCloakUserHandleService;



    @Test
    void testGetClientById_shouldExists(){

        ClientEntity mockClient = new ClientEntity();
        mockClient.setId(UUID.fromString("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e"));
        mockClient.setUsername("John");
        when(clientRepository.findById
                (UUID.fromString("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e")))
                .thenReturn(Optional.of(mockClient));

        // Act
        ResponseEntity<ClientResponseDto> clientServiceById =
                clientService.getById("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e");

        // Assert
        assertEquals("John", clientServiceById.getBody().getUsername());

    }

    @Test
    void testGetClientById_shouldNotExists(){

        when(clientRepository.findById(
                    UUID.fromString("1f9f3335-b1a1-45d2-b1e1-c406130708be"))
        ).thenReturn(Optional.empty());

        assertThrows(ClientHasBeenNotFoundException.class,
                () -> clientService.getById("1f9f3335-b1a1-45d2-b1e1-c406130708be"));

    }

    @Test
    void testPersistClient(){

        ClientRequestDto mockRequestDto = new ClientRequestDto();
        mockRequestDto.setId(UUID.fromString("7ea7aab3-cf19-414d-8bc6-a1e8506ddcd3"));
        mockRequestDto.setFirstName("ayrton");
        mockRequestDto.setLastName("de silva");
        mockRequestDto.setUsername("ayrton_senna");
        mockRequestDto.setEmail("ayrton123@gmail.com");
        mockRequestDto.setPassword("1234");

        ClientEntity mockEntity = new ClientEntity();
        mockEntity.setId(UUID.fromString("7ea7aab3-cf19-414d-8bc6-a1e8506ddcd3"));
        mockEntity.setFirstName("ayrton");
        mockEntity.setLastName("de silva");
        mockEntity.setUsername("ayrton_senna");
        mockEntity.setEmail("ayrton123@gmail.com");

        when(keyCloakUserHandleService.createUser(mockRequestDto.getUsername()
                , mockRequestDto.getLastName(),
                mockRequestDto.getFirstName(),
                mockRequestDto.getEmail(),
                mockRequestDto.getPassword(),"client"));

        when(clientRepository.save(mockEntity)).thenReturn(mockEntity);

        ResponseEntity<ClientResponseDto> savedEntity = clientService.persist(mockRequestDto);

        assertEquals(UUID.fromString("7ea7aab3-cf19-414d-8bc6-a1e8506ddcd3") ,
                    savedEntity.getBody().getId());



    }



}
