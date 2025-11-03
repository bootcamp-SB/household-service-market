package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ServiceGigEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ServiceGigRepository extends CrudRepository<ServiceGigEntity, UUID> {
}
