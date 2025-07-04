package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity , UUID> {

    Optional<ClientEntity>findByEmail(String email);



}
