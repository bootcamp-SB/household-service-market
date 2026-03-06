package service;

import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderResponseDto;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl;
import edu.bootcamp_sb.service_market.service.impl.ProviderServiceImpl;
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
class ProviderServiceTest {


    @Mock
    private ProviderRepository providerRepository;

    @InjectMocks
    private ProviderServiceImpl providerService;



    @Test
    void testGetProviderById_shouldExists(){

        ProviderEntity mockProvider = new ProviderEntity();
        mockProvider.setId(UUID.fromString("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e"));
        mockProvider.setFirstName("mariya");
        mockProvider.setContactNo("0785211458");
        mockProvider.setEmail("mariya@123gmail.com");
        when(providerRepository.findById
                (UUID.fromString("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e")))
                .thenReturn(Optional.of(mockProvider));

        // Act
        ResponseEntity<ProviderResponseDto> providerServiceById =
                providerService.getById(UUID.fromString("f3c9b7a2-8d41-4e6e-9a73-2c5f1b8d4a9e"));

        // Assert
        assertEquals("mariya", providerServiceById.getBody().getProviderDto().getFirstName());

    }
    
}
