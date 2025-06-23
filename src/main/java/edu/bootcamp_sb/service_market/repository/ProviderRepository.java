package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends CrudRepository<ProviderEntity , Integer> {

    Iterable<ProviderEntity> findAllByExpertise(String expertise);

    Optional<ProviderEntity> findByEmail(String email);

    Optional<ProviderEntity> findByContactNo(String contactNo);



}
