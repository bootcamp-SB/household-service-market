package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ReviewsEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.UUID;

public interface ReviewRepository extends CrudRepository<ReviewsEntity, UUID> {

    Iterable<ReviewsEntity> findAllByServiceGigEntityId(UUID id);

}
