package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientProfileRepository extends CrudRepository<ClientProfileEntity,Integer> {
}
