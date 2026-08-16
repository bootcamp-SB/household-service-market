package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    Iterable<BookingEntity> findAllByClientId(UUID id);

    Integer countByClientId(UUID userid);

    Iterable<BookingEntity> findByClientIdAndStatus(UUID clientId, String status);

    @Query("SELECT b FROM BookingEntity b WHERE b.serviceProvider.id = :providerId AND " +
           "(LOWER(b.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<BookingEntity> findByServiceProviderIdAndKeyword(
            @Param("providerId") UUID providerId,
            @Param("keyword") String keyword,
            Pageable pageable);

    @Query("SELECT b FROM BookingEntity b WHERE b.serviceProvider.id = :providerId")
    Page<BookingEntity> findAllByServiceProviderId(
            @Param("providerId") UUID providerId,
            Pageable pageable);

}

