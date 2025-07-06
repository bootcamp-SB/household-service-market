package edu.bootcamp_sb.service_market.dto;

import edu.bootcamp_sb.service_market.entity.ClientEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Interceptor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientProfileDto {


    private UUID id;

    private String profilePicUrl;

    private ClientEntity client;
}
