package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.AdminEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends CrudRepository<AdminEntity ,UUID> {
    Optional<AdminEntity> findByEmail(String email);

}
