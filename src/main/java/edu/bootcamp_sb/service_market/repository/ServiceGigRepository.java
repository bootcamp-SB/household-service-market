package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ServiceGigEntity;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.UUID;

public interface ServiceGigRepository extends JpaRepository<ServiceGigEntity, UUID> {

    long countByIsActive(Boolean isActive);

    Iterable<ServiceGigEntity> findAllByIsActive(Boolean isActive);

}
