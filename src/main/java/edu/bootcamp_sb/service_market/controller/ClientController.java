package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import edu.bootcamp_sb.service_market.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> persist(@RequestBody ClientDto clientDto){
        return clientService.persist(clientDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAll(){
        return clientService.getAll();
    }

    @DeleteMapping("/by-id")
    public ResponseEntity<Map<String,String>>deleteById(@RequestParam Integer id){
        return clientService.deleteById(id);
    }

    @PatchMapping("/by-id")
    public ResponseEntity<ClientDto> updateById(@RequestBody ClientDto clientDto){
        return clientService.updateByID(clientDto);
    }

}
