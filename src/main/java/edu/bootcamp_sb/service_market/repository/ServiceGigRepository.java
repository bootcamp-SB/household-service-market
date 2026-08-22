package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ServiceGigEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceGigRepository extends JpaRepository<ServiceGigEntity, UUID> {

    long countByIsActive(Boolean isActive);

    Iterable<ServiceGigEntity> findAllByIsActive(Boolean isActive);

    Page<ServiceGigEntity> findAllByIsActive(Boolean isActive, Pageable pageable);

    @Query("SELECT g FROM ServiceGigEntity g WHERE g.isActive = true AND " +
           "(LOWER(g.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.location) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.priceType) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.category.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.serviceGigProvider.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.serviceGigProvider.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<ServiceGigEntity> findActiveGigsByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Integer countByServiceGigProviderId(UUID id);

    Iterable<ServiceGigEntity> findAllByServiceGigProviderId(UUID id);

}

