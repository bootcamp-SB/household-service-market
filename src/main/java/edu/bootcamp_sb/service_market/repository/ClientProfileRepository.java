package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientProfileRepository extends CrudRepository<ClientProfileEntity, UUID> {


}
