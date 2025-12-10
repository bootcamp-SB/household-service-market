package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ReviewsDto;
import edu.bootcamp_sb.service_market.dto.reponse.ReviewResponseDto;
import edu.bootcamp_sb.service_market.entity.ReviewsEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ResponseEntity<ReviewResponseDto> giveAReview(ReviewsDto review);

    ResponseEntity<List<ReviewResponseDto>> getAllResponses();

    ResponseEntity<List<ReviewResponseDto>>getAllReviewInGig(UUID id);


}
