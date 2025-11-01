package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ProviderGigPosterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProviderPosterRepository extends CrudRepository<ProviderGigPosterEntity, UUID> {
}
