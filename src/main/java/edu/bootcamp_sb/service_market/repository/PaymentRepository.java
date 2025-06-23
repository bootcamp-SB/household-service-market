package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
