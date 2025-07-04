package edu.bootcamp_sb.service_market.repository;

import edu.bootcamp_sb.service_market.entity.JobEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends CrudRepository<JobEntity, UUID> {
}
