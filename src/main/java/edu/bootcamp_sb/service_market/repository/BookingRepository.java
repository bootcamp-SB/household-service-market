package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface BookingRepository extends CrudRepository<BookingEntity, UUID> {
}
