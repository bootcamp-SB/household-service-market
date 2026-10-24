package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.ProviderCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderCalendarRepository extends JpaRepository<ProviderCalendarEntity,Long> {
}
