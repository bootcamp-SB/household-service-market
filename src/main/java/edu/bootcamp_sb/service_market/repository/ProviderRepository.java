package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity , UUID> {

    Iterable<ProviderEntity> findAllByExpertise(String expertise);

    Optional<ProviderEntity> findByEmail(String email);

    Optional<ProviderEntity> findByContactNo(String contactNo);

    @Query("SELECT p FROM ProviderEntity p ORDER BY p.jobCount DESC")
    List<ProviderEntity> findTop5ByOrderByJobCountDesc(Pageable pageable);

}
